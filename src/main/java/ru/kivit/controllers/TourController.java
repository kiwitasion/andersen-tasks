package ru.kivit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.models.Tour;
import ru.kivit.services.TourService;

@Controller
@RequestMapping("/tours")
public class TourController {

    private final TourService service;

    @Autowired
    public TourController(TourService service) {
        this.service = service;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("tours", service.findAll());
        return "tour/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Tour tour = service.findById(id);
        model.addAttribute("tour", tour);
        return "tour/show";
    }

    @GetMapping("/new")
    public String newTour(@ModelAttribute("tour") Tour tour) {
        return "tour/new";
    }

    @PostMapping
    public String create(@ModelAttribute("tour") Tour tour) {
        service.save(tour);
        return "redirect:/tours";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("tour", service.findById(id));
        return "tour/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("tour") Tour tour, @PathVariable("id") Long id) {
        service.update(tour, id);
        return "redirect:/tours";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/tours";
    }
}
