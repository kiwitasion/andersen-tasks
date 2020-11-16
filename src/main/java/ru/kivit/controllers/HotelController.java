package ru.kivit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.dao.HotelDAO;
import ru.kivit.models.Hotel;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    private final HotelDAO dao;

    public HotelController(HotelDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("hotels", dao.findAll());
        return "hotel/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Hotel tour = dao.findById(id);
        model.addAttribute("hotel", tour);
        return "hotel/show";
    }

    @GetMapping("/new")
    public String newHotel(@ModelAttribute("hotel") Hotel hotel) {
        return "hotel/new";
    }

    @PostMapping
    public String create(@ModelAttribute("hotel") Hotel hotel) {
        dao.save(hotel);
        return "redirect:/hotels";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("hotel", dao.findById(id));
        return "hotel/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("hotel") Hotel hotel, @PathVariable("id") Long id) {
        dao.update(hotel, id);
        return "redirect:/hotels";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        dao.deleteById(id);
        return "redirect:/hotels";
    }
}
