package pl.camp.it.services;

import pl.camp.it.model.User;
import pl.camp.it.model.forms.Register;

public interface IUserService {
    User authenticate(String login, String pass);
    boolean register(Register register);
}
