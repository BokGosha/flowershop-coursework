package edu.bokgosha.flowershop.controllers;

import edu.bokgosha.flowershop.services.OrderService;
import edu.bokgosha.flowershop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userServiceImpl;
    private final OrderService orderService;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userServiceImpl.getAllUsers());

        return "admin";
    }

    @GetMapping("{id}")
    public String getUser(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("user", userServiceImpl.getUserById(userId));
        model.addAttribute("orders", orderService.getOrdersByUserId(userId));

        return "userInformation";
    }
}
