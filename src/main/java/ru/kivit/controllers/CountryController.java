package ru.kivit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.models.Country;
import ru.kivit.services.CountryService;

@Controller
@RequestMapping("/countries")
@PreAuthorize("hasAuthority('ADMIN')")
public class CountryController {

    private final CountryService service;

    @Autowired
    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("countries", service.findAll());
        return "country/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Country country = service.findById(id);
        model.addAttribute("country", country);
        return "country/show";

    }

    @GetMapping("/new")
    public String newCountry(@ModelAttribute("country") Country country) {
        return "country/new";
    }

    @PostMapping
    public String create(@ModelAttribute("country") Country country) {
        service.save(country);
        return "redirect:/countries";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("country", service.findById(id));
        return "country/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("country") Country updateCountry, @PathVariable("id") Long id) {
        service.update(updateCountry, id);
        return "redirect:/countries";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/countries";
    }

}
