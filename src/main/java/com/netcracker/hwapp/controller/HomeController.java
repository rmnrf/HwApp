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

    @GetMapping("/newTaskForm")
    public String newTaskFormPage() {
        return "new_task";
    }

    @GetMapping("/editTaskForm")
    public String editTaskFormPage() {
        return "edit_task";
    }

    @GetMapping("/showTaskForm")
    public String showTaskFormPage() {
        return "show_task";
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

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/rules")
    public String rulesPage() {
        return "rules";
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

    @GetMapping("/usersList")
    public String usersListPage() {
        return "/list/users_list";
    }
}