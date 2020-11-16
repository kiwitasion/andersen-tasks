package ru.kivit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kivit.dao.OrderDAO;
import ru.kivit.models.Order;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderDAO dao;

    public OrderController(OrderDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("orders", dao.findAll());
        return "order/showAll";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Order order = dao.findById(id);
        model.addAttribute("order", order);
        return "order/show";
    }

    @GetMapping("/new")
    public String newOrder(@ModelAttribute("order") Order order) {
        return "order/new";
    }

    @PostMapping
    public String create(@ModelAttribute("order") Order order) {
        dao.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("order", dao.findById(id));
        return "order/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("order") Order order, @PathVariable("id") Long id) {
        dao.update(order, id);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        dao.deleteById(id);
        return "redirect:/orders";
    }
}
