package ru.kivit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.models.Hotel;
import ru.kivit.services.HotelService;

@Controller
@RequestMapping("/hotels")
@PreAuthorize("hasAuthority('ADMIN')")
public class HotelController {

    private final HotelService service;

    @Autowired
    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("hotels", service.findAll());
        return "hotel/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Hotel tour = service.findById(id);
        model.addAttribute("hotel", tour);
        return "hotel/show";
    }

    @GetMapping("/new")
    public String newHotel(@ModelAttribute("hotel") Hotel hotel) {
        return "hotel/new";
    }

    @PostMapping
    public String create(@ModelAttribute("hotel") Hotel hotel) {
        service.save(hotel);
        return "redirect:/hotels";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("hotel", service.findById(id));
        return "hotel/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("hotel") Hotel hotel, @PathVariable("id") Long id) {
        service.update(hotel, id);
        return "redirect:/hotels";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/hotels";
    }
}
