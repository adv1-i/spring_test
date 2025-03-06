package org.alishev.course.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name too long or too short")
    private String name;
    @Range(min = 1900, max = 2025, message = "Not correct birth date")
    private int birth_date;


    public Person() {
    }

    public Person(int id, String name, int birth_date) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(int birth_date) {
        this.birth_date = birth_date;
    }
}
