package ru.kivit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.models.Review;
import ru.kivit.services.ReviewService;


@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("reviews", service.findAll());
        return "review/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Review review = service.findById(id);
        model.addAttribute("review", review);
        return "review/show";
    }

    @GetMapping("/new")
    public String newReview(@ModelAttribute("review") Review review) {
        return "review/new";
    }

    @PostMapping
    public String create(@ModelAttribute("review") Review review) {
        service.save(review);
        return "redirect:/reviews";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("review", service.findById(id));
        return "review/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("review") Review review, @PathVariable("id") Long id) {
        service.update(review, id);
        return "redirect:/reviews";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/reviews";
    }

}
