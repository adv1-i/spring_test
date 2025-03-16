package com.bootapp.springboot.controllers;

import com.bootapp.springboot.models.Book;
import com.bootapp.springboot.models.Person;
import com.bootapp.springboot.services.BookService;
import com.bootapp.springboot.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping()
    public String getAllBooks(Model model,
                              @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(value = "books_per_page", required = false, defaultValue = "3") Integer books_per_page,
                              @RequestParam(value = "sort_by_year", required = false, defaultValue = "false") Boolean sort_by_year) {

        Page<Book> books = bookService.findAll(page, books_per_page, sort_by_year);
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String getBookPage(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findBookPageById(id));

        Optional<Person> bookOwner = bookService.findBookOwnerById(id);

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", personService.findAll());
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

        bookService.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookForm(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("book", bookService.findEditBookFormById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book,
                           BindingResult bindingResult,
                           @PathVariable("id") Integer id) {

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookService.editBook(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookService.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String searchForm(Model model) {
        return "books/search";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("title") String title) {
        List<Book> books = bookService.searchBook(title);
        model.addAttribute("books", books);
        return "books/search";
    }

}
