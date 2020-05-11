package pl.camp.it.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.model.Book;
import pl.camp.it.services.IBookService;
import pl.camp.it.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @Autowired
    IBookService bookService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String booksView(Model model) {

        if(!sessionObject.isLogged()) {
            return "redirect:/login";
        }

        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("filter", "");
        return "books";
    }

    @RequestMapping(value = "/bookFilter", method = RequestMethod.POST)
    public String booksFilteredView(@RequestParam String filter, Model model) {

        addCommonParametersToModel(model);

        if(!sessionObject.isLogged()) {
            return "redirect:/login";
        }

        /*List<Book> books = bookService.getAllBooks();

        List<Book> filteredBooks = new ArrayList<>();

        for (Book book : books) {
            if(book.toString().contains(filter)) {
                filteredBooks.add(book);
            }
        }*/

        List<Book> filteredBooks = bookService.getAllBooks().stream()
                .filter(x -> x.toString().contains(filter))
                .collect(Collectors.toList());

        model.addAttribute("books", filteredBooks);
        model.addAttribute("filter", filter);
        return "books";
    }


    private void addCommonParametersToModel(Model model) {
        //uzupełnia model
        model.addAttribute("jakisAtrybut", "wartość");
        //
        //
        //
    }
}
