package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showMainPage(Model model, Principal principal) throws UserNotFoundException {
        if (principal == null) {
            return "index";
        }
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "index";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/rules")
    public String showRulesPage() {
        return "rules";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
