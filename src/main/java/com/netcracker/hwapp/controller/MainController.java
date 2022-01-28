package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String showMainPage(Model model, Principal principal) {
        if (principal == null) {
            return "index";
        }
        model.addAttribute("user", userRepo.findByEmail(principal.getName()));
        return "index";
    }


}
