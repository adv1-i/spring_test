package org.alishev.course.services;

import org.alishev.course.model.Book;
import org.alishev.course.model.Person;
import org.alishev.course.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> findAll(int page, int books_per_page, boolean sort_by_year) {
        Pageable pageable;

        if (sort_by_year) {
            pageable = PageRequest.of(page, books_per_page, Sort.by(Sort.Direction.ASC, "releaseDate"));
        } else {
            pageable = PageRequest.of(page, books_per_page);
        }

        return bookRepository.findAll(pageable);
    }

    public List<Book> searchBook(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Transactional
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public Book findEditBookFormById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void editBook(int id, Book book) {
        Book byId = bookRepository.findById(id).get();
        book.setId(id);
        book.setPerson(byId.getPerson());
        bookRepository.save(book);
    }

    public Book findBookPageById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Optional<Person> findBookOwnerById(int id) {
        return bookRepository.findById(id).map(Book::getPerson);
    }

    @Transactional
    public void release(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTakenAt(null);
        book.setPerson(null);
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTakenAt(new Date());
        book.setPerson(selectedPerson);
    }
}
