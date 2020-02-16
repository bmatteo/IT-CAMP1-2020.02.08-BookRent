package pl.camp.it.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.User;
import pl.camp.it.model.UserRole;

import java.sql.*;

@Repository
public class DBUserDAOImpl implements IUserDAO {

    @Autowired
    Connection connection;
    @Override
    public void persistUser(User user) {
        try {
            String sql;
            PreparedStatement ps;
            if(user.getId() == 0) {
                sql = "INSERT INTO tuser (login, pass, role) VALUES (?, ?, ?);";
                ps = this.connection.prepareStatement(sql);
            } else {
                sql = "UPDATE tuser SET login = ?, pass = ?, role = ? WHERE id = ?;";
                ps = this.connection.prepareStatement(sql);
                ps.setInt(4, user.getId());
            }

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPass());
            ps.setString(3, user.getRole().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Błąd dodawania usera !!");
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            String sql = "SELECT * FROM tuser WHERE login = ?;";

            PreparedStatement ps = this.connection.prepareStatement(sql);

            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = mapResultSetToUser(rs);

                return user;
            }
        } catch (SQLException e) {
            System.out.println("Błąd pobierania usera po loginie !");
            e.printStackTrace();
        }

        return null;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setPass(rs.getString("pass"));
        user.setRole(UserRole.valueOf(rs.getString("role")));

        return user;
    }
}
