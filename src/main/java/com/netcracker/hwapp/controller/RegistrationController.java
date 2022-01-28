package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.dto.StudentRegistrationDto;
import com.netcracker.hwapp.dto.TeacherRegistrationDto;
import com.netcracker.hwapp.dto.UserRegistrationDto;
import com.netcracker.hwapp.enums.UserType;
import com.netcracker.hwapp.exception.UserAlreadyExistsException;
import com.netcracker.hwapp.model.Discipline;
import com.netcracker.hwapp.model.Faculty;
import com.netcracker.hwapp.repository.DisciplineRepo;
import com.netcracker.hwapp.repository.FacultyRepo;
import com.netcracker.hwapp.repository.GroupRepo;
import com.netcracker.hwapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    @Autowired
    private DisciplineRepo disciplineRepo;

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private FacultyRepo facultyRepo;

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

            //List<Faculty> faculty = new ArrayList<>();
            //model.addAttribute("faculty", faculty);
            model.addAttribute("faculties", facultyRepo.findAll());

            //List<Discipline> discipline = new ArrayList<>();
            //model.addAttribute("discipline", discipline);
            model.addAttribute("disciplines", disciplineRepo.findAll());

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
            return "redirect:/?error";
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
            return "redirect:/?error";
        }
    }
}
