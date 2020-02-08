package pl.camp.it.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage() {
        return "main";
    }
}
