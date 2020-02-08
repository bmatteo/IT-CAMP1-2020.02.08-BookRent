package pl.camp.it.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.User;
import pl.camp.it.services.IUserService;

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
}
