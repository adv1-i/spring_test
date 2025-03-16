package org.alishev.course.services;

import org.alishev.course.model.Book;
import org.alishev.course.model.Person;
import org.alishev.course.repositories.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }
    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        personRepository.save(person);
    }
    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public Person getFormPerson(int id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    public Optional<Person> getPersonName(String name) {
        return personRepository.findPersonByName(name);
    }

    public List<Book> findBooksByPersonId(int id) {
        Optional<Person> person = personRepository.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBookList());
            person.get().getBookList().forEach(book -> {
                long diffInMillis = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
                if (diffInMillis > 864000000)
                    book.setExpired(true);
            });
            return person.get().getBookList();
        } else {
            return Collections.emptyList();
        }
    }

}
