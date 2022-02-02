package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.dto.StudentRegistrationDto;
import com.netcracker.hwapp.dto.TeacherRegistrationDto;
import com.netcracker.hwapp.enums.UserType;
import com.netcracker.hwapp.service.DisciplineService;
import com.netcracker.hwapp.service.FacultyService;
import com.netcracker.hwapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private DisciplineService disciplineService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("student")
    public StudentRegistrationDto studentRegistrationDto() {
        return new StudentRegistrationDto();
    }

    @ModelAttribute("teacher")
    public TeacherRegistrationDto teacherRegistrationDto() {
        return new TeacherRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(@RequestParam(name = "userType", required = false)
                                                   String userType, Model model) {
        if (userType == null) {
            return "reg_select_type";
        }

        boolean contains = false;
        for (UserType type : UserType.values()) {
            if (userType.equals(type.name())) {
                contains = true;
                break;
            }
        }

        if (contains) {
            model.addAttribute("userType", userType);
            model.addAttribute("faculties", facultyService.findAll());
            model.addAttribute("disciplines", disciplineService.findAll());
            return "registration";
        }

        return "reg_select_type";
    }

    @PostMapping("/student")
    public String registerStudentAccount(
            @ModelAttribute("student")
            @Valid StudentRegistrationDto studentRegistrationDto) {
        try {
            userService.create(studentRegistrationDto);
            return "redirect:/login?success";
        } catch (Exception e) {
            return "redirect:/login?error";
        }
    }

    @PostMapping("/teacher")
    public String registerTeacherAccount(
            @ModelAttribute("teacher")
            @Valid TeacherRegistrationDto teacherRegistrationDto) {
        try {
            userService.create(teacherRegistrationDto);
            return "redirect:/login?success";
        } catch (Exception e) {
            return "redirect:/login?error";
        }
    }
}
