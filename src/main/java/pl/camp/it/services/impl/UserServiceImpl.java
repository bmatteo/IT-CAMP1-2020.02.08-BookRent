package pl.camp.it.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.User;
import pl.camp.it.model.UserRole;
import pl.camp.it.model.forms.Register;
import pl.camp.it.services.IUserService;

import java.util.Random;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Override
    public User authenticate(String login, String pass) {
        User userFromDb = userDAO.getUserByLogin(login);

        String userHashedPassword = DigestUtils.md5Hex(pass);

        if(userFromDb.getPass().equals(userHashedPassword)) {
            return userFromDb;
        } else {
            return null;
        }
    }

    @Override
    public boolean register(Register register) {
        if(register.getPass().equals(register.getPass2())) {
            User userFromDb = userDAO.getUserByLogin(register.getLogin());

            if(userFromDb == null) {
                User user = new User();
                user.setLogin(register.getLogin());
                user.setPass(DigestUtils.md5Hex(register.getPass()));
                user.setRole(UserRole.USER);

                userDAO.persistUser(user);

                return true;
            }
        }

        return false;
    }
}
