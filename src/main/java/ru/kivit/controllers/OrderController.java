package ru.kivit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.models.Order;
import ru.kivit.services.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("orders", service.findAll());
        return "order/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Order order = service.findById(id);
        model.addAttribute("order", order);
        return "order/show";
    }

    @GetMapping("/new")
    public String newOrder(@ModelAttribute("order") Order order) {
        return "order/new";
    }

    @PostMapping
    public String create(@ModelAttribute("order") Order order) {
        service.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("order", service.findById(id));
        return "order/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("order") Order order, @PathVariable("id") Long id) {
        service.update(order, id);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/orders";
    }
}
