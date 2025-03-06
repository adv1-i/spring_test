package org.alishev.course.controller;

import org.alishev.course.dao.PersonDAO;
import org.alishev.course.model.Person;
import org.alishev.course.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;

    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String getAllPeople(Model model) {
        model.addAttribute("people", personDAO.getAllpeople());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String getPersonPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonPage(id));
        model.addAttribute("book", personDAO.getBooksByPersonId(id));
        return "people/page";
    }

    @GetMapping("/new")
    public String createPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "people/create";
    }
    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/create";
        }

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPersonForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getFormPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String editPerson(@ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        personDAO.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }
}
