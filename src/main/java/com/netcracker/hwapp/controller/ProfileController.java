package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.dto.StudentRegistrationDto;
import com.netcracker.hwapp.dto.UserRegistrationDto;
import com.netcracker.hwapp.dto.UserUpdateDto;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String showUserProfile(Model model, Principal principal) throws UserNotFoundException {
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "/profile/show_profile";
    }

    @GetMapping("/editProfile")
    public String showEditProfileForm(Model model, Principal principal) throws UserNotFoundException {
        model.addAttribute("user", userService.findByEmail(principal.getName()));
        return "/profile/edit_profile";
    }

    @PostMapping("/editProfile")
    public String updateUserProfile(Model model,
                                    @Valid UserUpdateDto dto,
                                    Principal principal) {
        try {
            userService.update(dto, principal);
            return "redirect:/editProfile?success";
        } catch (Exception e) {
            return "redirect:/?error";
        }
    }
}
