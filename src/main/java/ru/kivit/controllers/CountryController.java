package ru.kivit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.dao.CountryDAO;
import ru.kivit.models.Country;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryDAO dao;

    public CountryController(CountryDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public String showAll(Model model) {
        System.out.println("hello");
        model.addAttribute("countries", dao.findAll());
        return "country/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Country country = dao.findById(id);
        model.addAttribute("country", country);
        return "country/show";
    }

    @GetMapping("/new")
    public String newCountry(@ModelAttribute("country") Country country) {
        return "country/new";
    }

    @PostMapping
    public String create(@ModelAttribute("country") Country country) {
        dao.save(country);
        return "redirect:/countries";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("country", dao.findById(id));
        return "country/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("country") Country updateCountry, @PathVariable("id") Long id) {
        dao.update(updateCountry, id);
        return "redirect:/countries";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        dao.deleteById(id);
        return "redirect:/countries";
    }

}
