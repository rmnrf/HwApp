package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.dto.UserCreateDTO;
import com.netcracker.hwapp.dto.UserUpdateDTO;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.User;
import com.netcracker.hwapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model) {
        // Create model attribute to bind form data
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new_user";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        // Call delete user method
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping("/users")
    public String showUserProfile(@RequestParam Long id,
                                  Model model) throws UserNotFoundException {
        model.addAttribute("user", userService.getUserById(id));
        return "user_profile";
    }

    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable (value = "pageNumber") Integer pageNumber,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        Integer pageSize = 5;

        Page<User> page = userService.findPaginated(pageNumber, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listUsers", listUsers);
        return "index";
    }

    @GetMapping("/showUpdateUserForm/{id}")
    public String showUpdateUserForm(@PathVariable(value = "id") Long id, Model model) throws UserNotFoundException {
        // Get user from service
        User user = userService.getUserById(id);
        // Set user as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "update_user";
    }
}
