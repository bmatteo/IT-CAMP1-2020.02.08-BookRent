package pl.camp.it.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.model.Book;
import pl.camp.it.services.IBookService;

@Controller
public class HaksyController {

    @Autowired
    IBookService bookService;




    

    @RequestMapping(value = "/g200", method = RequestMethod.GET)
    public String addBooks() {

        Book book = new Book();
        book.setTitle("Spring w akcji. Wydanie V");
        book.setAuthor("Craig Walls");
        book.setIsbn("hagsfdhj");

        Book book2 = new Book();
        book2.setTitle("Czysty kod. Podręcznik dobrego programisty");
        book2.setAuthor("Robert C. Martin");
        book2.setIsbn("978-83-283-0234-1, 9788328302341");

        Book book3 = new Book();
        book3.setTitle("Czysta architektura. Struktura i design oprogramowania. Przewodnik dla profesjonalistów");
        book3.setAuthor("Robert C. Martin");
        book3.setIsbn("978-83-283-4225-5, 9788328342255");

        Book book4 = new Book();
        book4.setTitle("Młodzi giganci programowania. Scratch");
        book4.setAuthor("Radosław Kulesza");
        book4.setIsbn("978-83-283-4336-8, 9788328343368");

        bookService.persistBook(book);
        bookService.persistBook(book2);
        bookService.persistBook(book3);
        bookService.persistBook(book4);

        return "redirect:/login";
    }
}
