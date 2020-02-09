package pl.camp.it.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.model.Book;
import pl.camp.it.model.User;
import pl.camp.it.model.forms.Register;
import pl.camp.it.services.IUserService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {

        if(sessionObject.isLogged()) {
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if(sessionObject.isLogged()) {
            return "redirect:/main";
        }
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginAction(@RequestParam String login,
                              @RequestParam String pass) {
        if(sessionObject.isLogged()) {
            return "redirect:/main";
        }

        User user = userService.authenticate(login,pass);

        if(user == null) {
            sessionObject.setLogged(false);
            return "loginForm";
        } else {
            sessionObject.setLogged(true);
            sessionObject.setUser(user);
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        if(sessionObject.isLogged()) {
            return "redirect:/main";
        }
        model.addAttribute("registerModel", new Register());
        model.addAttribute("book", new Book());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerAction(@ModelAttribute Register registerZFormularza,
                                 Model opcjonalnyModelNigdyNieUżyty) {
        if(sessionObject.isLogged()) {
            return "redirect:/main";
        }
        boolean registered = userService.register(registerZFormularza);

        if(registered) {
            return "redirect:/main";
        } else {
            return "redirect:/register";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        sessionObject.setLogged(false);
        return "redirect:/login";
    }
}
