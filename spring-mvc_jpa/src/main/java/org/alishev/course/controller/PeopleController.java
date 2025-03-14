package org.alishev.course.controller;

import javax.validation.Valid;

import org.alishev.course.dao.PersonDAO;
import org.alishev.course.model.Person;
import org.alishev.course.service.ItemService;
import org.alishev.course.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final ItemService itemService;

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PeopleService peopleService, ItemService itemService, PersonDAO personDAO) {
        this.peopleService = peopleService;
        this.itemService = itemService;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
//        model.addAttribute("people", peopleService.findAll());
//        itemService.findByItemName("Airpods");
//        itemService.findByOwner(peopleService.findAll().get(0));

        personDAO.testNPlus1();
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}


