package org.alishev.course.dao;

import org.alishev.course.model.Book;
import org.alishev.course.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void createBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, release_date) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getRelease_date());
    }


    public Book getEditBookForm(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void editBook(Book book) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, release_date=?",
                book.getTitle(), book.getAuthor(), book.getRelease_date());
    }

    public Book getBookPage(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Person.id = Book.person_id WHERE Book.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(), id);
    }
}
