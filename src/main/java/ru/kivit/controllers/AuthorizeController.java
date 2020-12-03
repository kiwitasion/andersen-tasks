package ru.kivit.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kivit.models.User;
import ru.kivit.services.UserService;

@Controller
@AllArgsConstructor
public class AuthorizeController {

    private final UserService service;

    @GetMapping("/login")
    public String login() {
        return "registration/login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        return "registration/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("user") User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "registration/registration";
        }
        if (!service.save(user)){
            return "registration/registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/start-page")
    public String startPage() {
        return "registration/startPage";
    }
}
