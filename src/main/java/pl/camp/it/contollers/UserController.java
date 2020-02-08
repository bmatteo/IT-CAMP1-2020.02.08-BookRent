package pl.camp.it.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.model.User;
import pl.camp.it.services.IUserService;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginAction(@RequestParam String login,
                              @RequestParam String pass) {


        User user = userService.authenticate(login,pass);

        if(user == null) {
            return "loginForm";
        } else {
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "registerForm";
    }
}
