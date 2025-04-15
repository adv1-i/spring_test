package com.example.springrest.FirstRestApp.service;

import com.example.springrest.FirstRestApp.model.Person;
import com.example.springrest.FirstRestApp.repository.PeopleRepository;
import com.example.springrest.FirstRestApp.util.PersonNotCreatedException;
import com.example.springrest.FirstRestApp.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Not found person with id = " + id));
    }

    @Transactional
    public void save(Person person) {
        enrichCreatePerson(person);
        peopleRepository.save(person);
    }

    public void enrichCreatePerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setCreatedWho("ADMIN");
    }

    public void enrichUpdatePerson(Person person) {
        person.setUpdatedAt(LocalDateTime.now());
    }

    @Transactional
    public Person update(int id, Person updatedPerson) {
        Optional<Person> existedPerson = peopleRepository.findById(id);
        if (existedPerson.isPresent()) {
            Person person = existedPerson.get();
            person.setName(updatedPerson.getName());
            person.setAge(updatedPerson.getAge());
            person.setEmail(updatedPerson.getEmail());
            enrichUpdatePerson(person);
            return peopleRepository.save(person);
        } else {
            throw new PersonNotFoundException("Not found person with id = " + id);
        }
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
