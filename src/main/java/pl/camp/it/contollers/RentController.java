package pl.camp.it.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.model.Book;
import pl.camp.it.model.Rent;
import pl.camp.it.services.IBookService;
import pl.camp.it.services.IRentService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RentController {

    @Autowired
    IRentService rentService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/rent/{id}", method = RequestMethod.GET)
    public String rentBook(@PathVariable int id, Model model) {

        if(!sessionObject.isLogged()) {
            return "redirect:/login";
        }

        boolean isRent = rentService.rentBookById(id);
        if(isRent) {
            model.addAttribute("rentMessage", "Wypożyczenie udane !!");
        } else {
            model.addAttribute("rentMessage", "Wypożyczenie nieudane !!");
        }

        List<Rent> userRents =
                rentService.getAllRentsForUser(sessionObject.getUser());

        model.addAttribute("userRents", userRents);

        return "myRents";
    }

    @RequestMapping(value = "/myRents", method = RequestMethod.GET)
    public String rentsView(Model model) {
        if(!sessionObject.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("rentMessage", "");

        List<Rent> userRents =
                rentService.getAllRentsForUser(sessionObject.getUser());

        model.addAttribute("userRents", userRents);

        return "myRents";
    }
}
