package pl.camp.it.dao.impl;

import org.springframework.stereotype.Repository;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.User;
import pl.camp.it.model.UserRole;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements IUserDAO {

    private String fileName = "/home/mateusz/ITCamp-Kraków/2020.02.08-bookRent/src/main/resources/users.txt";

    @Override
    public void persistUser(User user) {
        List<User> temporaryBase = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                temporaryBase.add(convertLineToUserObject(line));
            }

            for (User tempUser : temporaryBase) {
                if(tempUser.getId() == user.getId()) {
                    temporaryBase.remove(tempUser);
                    break;
                }
            }

            temporaryBase.add(user);

            for (User tempUser : temporaryBase) {
                bufferedWriter.write(tempUser.toString());
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByLogin(String login) {
        List<User> temporaryBase = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                temporaryBase.add(convertLineToUserObject(line));
            }

            for (User tempUser : temporaryBase) {
                if(tempUser.getLogin().equals(login)) {
                    return tempUser;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private User convertLineToUserObject(String line) {
        String[] fields = line.split(";");

        User result = new User();

        result.setId(Integer.parseInt(fields[0]));
        result.setLogin(fields[1]);
        result.setPass(fields[2]);
        result.setRole(UserRole.valueOf(fields[3]));

        return result;
    }
}
