package ru.kivit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.dao.UserDAO;
import ru.kivit.models.User;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDAO dao;

    public UserController(UserDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("users", dao.findAll());
        return "user/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        User user = dao.findById(id);
        model.addAttribute("user", user);
        return "user/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        dao.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", dao.findById(id));
        return "user/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        dao.update(user, id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        dao.deleteById(id);
        return "redirect:/users";
    }
}
