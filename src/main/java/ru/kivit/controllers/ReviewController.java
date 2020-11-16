package ru.kivit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.dao.ReviewDAO;
import ru.kivit.models.Review;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewDAO dao;

    public ReviewController(ReviewDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("reviews", dao.findAll());
        return "review/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Review review = dao.findById(id);
        model.addAttribute("review", review);
        return "review/show";
    }

    @GetMapping("/new")
    public String newReview(@ModelAttribute("review") Review review) {
        return "review/new";
    }

    @PostMapping
    public String create(@ModelAttribute("review") Review review) {
        dao.save(review);
        return "redirect:/reviews";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("review", dao.findById(id));
        return "review/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("review") Review review, @PathVariable("id") Long id) {
        dao.update(review, id);
        return "redirect:/reviews";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        dao.deleteById(id);
        return "redirect:/reviews";
    }

}
