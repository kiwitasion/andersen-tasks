package ru.kivit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.models.User;
import ru.kivit.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("users", service.findAll());
        return "user/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        User user = service.findById(id);
        model.addAttribute("user", user);
        return "user/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        service.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", service.findById(id));
        return "user/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        service.update(user, id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/users";
    }
}
