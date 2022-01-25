package com.netcracker.hwapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {

    @GetMapping("/home")
    String index(Model model) {
        return "home/homeNotSignedIn";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @GetMapping("/hero")
    public String heroPage() {
        return "hero";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "reg_select_type";
    }

    @GetMapping("/registrationForm")
    public String registrationFormPage() {
        return "registration";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
}
