package com.bootapp.springboot.repositories;

import com.bootapp.springboot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findPersonByName(String name);
}
