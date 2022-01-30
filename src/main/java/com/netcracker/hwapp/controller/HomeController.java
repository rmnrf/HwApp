package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.model.User;
import com.netcracker.hwapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/newTaskForm")
    public String newTaskFormPage() {
        return "/task/new_task";
    }

    @GetMapping("/editTaskForm")
    public String editTaskFormPage() {
        return "/task/edit_task";
    }

    @GetMapping("/showTaskForm")
    public String showTaskFormPage() {
        return "/task/show_task";
    }

    @GetMapping("/newSolutionForm")
    public String newSolutionFormPage() {
        return "/solution/new_solution";
    }

    @GetMapping("/editSolutionForm")
    public String editSolutionFormPage() {
        return "/solution/edit_solution";
    }

    @GetMapping("/showSolutionForm")
    public String showSolutionFormPage() {
        return "/solution/show_solution";
    }

    @GetMapping("/editProfileForm")
    public String editProfileFormPage() {
        return "/profile/edit_profile";
    }

    @GetMapping("/tasksList")
    public String tasksListPage() {
        return "/list/tasks_list";
    }

    @GetMapping("/solutionsList")
    public String solutionsListPage() {
        return "/list/solutions_list";
    }

    @GetMapping("/ ")
    public String usersListPage() {
        return "/list/users_list";
    }
}