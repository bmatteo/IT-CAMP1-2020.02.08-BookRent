package pl.camp.it.services;

import pl.camp.it.model.User;

public interface IUserService {
    User authenticate(String login, String pass);
}
