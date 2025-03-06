package org.alishev.course.controller;

import org.alishev.course.dao.BookDAO;
import org.alishev.course.dao.PersonDAO;
import org.alishev.course.model.Book;
import org.alishev.course.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getAllBooks(Model model) {
        model.addAttribute("book", bookDAO.getAllBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String getBookPage(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.getBookPage(id));

        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", personDAO.getAllpeople());
        }
        return "books/page";
    }

    @GetMapping("/new")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/create";
    }
    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/create";
        }

        bookDAO.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.getEditBookForm(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDAO.editBook(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookDAO.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }
}
