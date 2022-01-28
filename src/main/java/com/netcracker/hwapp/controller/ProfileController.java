package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/profile")
    public String showUserProfile(Model model,
                                  Principal principal) {
        model.addAttribute("user", userRepo.findByEmail(principal.getName()));
        return "/profile/show_profile";
    }


}
