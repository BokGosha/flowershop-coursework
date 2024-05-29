package edu.bokgosha.flowershop.controllers;

import edu.bokgosha.flowershop.entities.User;
import edu.bokgosha.flowershop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userServiceImpl;

    @GetMapping("login")
    public String getRegistration(Model model) {
        model.addAttribute("userReg", new User());

        return "enter";
    }

    @PostMapping("reg")
    public String addNewUser(@ModelAttribute("userReg") User user) {
        userServiceImpl.createUser(user);

        return "redirect:login";
    }
}
