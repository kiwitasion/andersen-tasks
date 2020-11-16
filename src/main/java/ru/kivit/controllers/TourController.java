package ru.kivit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.dao.TourDAO;
import ru.kivit.models.Tour;

@Controller
@RequestMapping("/tours")
public class TourController {

    private final TourDAO dao;

    public TourController(TourDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("tours", dao.findAll());
        return "tour/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Tour tour = dao.findById(id);
        model.addAttribute("tour", tour);
        return "tour/show";
    }

    @GetMapping("/new")
    public String newTour(@ModelAttribute("tour") Tour tour) {
        return "tour/new";
    }

    @PostMapping
    public String create(@ModelAttribute("tour") Tour tour) {
        dao.save(tour);
        return "redirect:/tours";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("tour", dao.findById(id));
        return "tour/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("tour") Tour tour, @PathVariable("id") Long id) {
        dao.update(tour, id);
        return "redirect:/tours";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        dao.deleteById(id);
        return "redirect:/tours";
    }
}
