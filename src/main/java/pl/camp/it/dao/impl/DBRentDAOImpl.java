package pl.camp.it.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IRentDAO;
import pl.camp.it.model.Rent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DBRentDAOImpl implements IRentDAO {

    @Autowired
    Connection connection;

    @Override
    public void persistRent(Rent rent) {
        try {
            String sql;
            PreparedStatement ps;
            if(rent.getId() == 0) {
                sql = "INSERT INTO trent (startDate, bookId, userId, active) VALUES (?, ?, ?, ?);";
                ps = this.connection.prepareStatement(sql);
            } else {
                sql = "UPDATE trent SET startDate = ?, bookId = ?, userId = ?, active = ? WHERE id = ?;";
                ps = this.connection.prepareStatement(sql);
                ps.setInt(5, rent.getId());
            }

            ps.setDate(1, Date.valueOf(rent.getStartDate()));
            ps.setInt(2, rent.getBookId());
            ps.setInt(3, rent.getUserId());
            ps.setBoolean(4, rent.isActive());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Błąd dodawania wypożyczenia !!");
            e.printStackTrace();
        }
    }

    @Override
    public List<Rent> getRentsByBookId(int bookId) {

        List<Rent> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM trent WHERE bookId = ?;";

            PreparedStatement ps = this.connection.prepareStatement(sql);

            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rent rent = mapResultSetToRent(rs);

                result.add(rent);
            }
        } catch (SQLException e) {
            System.out.println("Błąd pobierania wypożyczenia po id książki !");
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Rent> getAllRents() {
        List<Rent> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM trent;";

            PreparedStatement ps = this.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Rent rent = mapResultSetToRent(rs);

                result.add(rent);
            }
        } catch (SQLException e) {
            System.out.println("Błąd pobierania wszystkich wypożyczeń !");
            e.printStackTrace();
        }

        return result;
    }

    private Rent mapResultSetToRent(ResultSet rs) throws SQLException {
        Rent rent = new Rent();

        rent.setId(rs.getInt("id"));
        rent.setStartDate(rs.getDate("startDate").toLocalDate());
        rent.setBookId(rs.getInt("bookId"));
        rent.setUserId(rs.getInt("userId"));
        rent.setActive(rs.getBoolean("active"));

        return rent;
    }
}
