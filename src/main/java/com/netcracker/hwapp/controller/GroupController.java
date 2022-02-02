package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Group;
import com.netcracker.hwapp.model.Student;
import com.netcracker.hwapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private StudentService studentService;

    @PreAuthorize("hasAuthority('student:perms')")
    @GetMapping
    public String showGroupPage(Model model, Principal principal) throws UserNotFoundException {
        Group group = studentService.findByEmail(principal.getName()).getGroup();
        List<Student> students = null;
        if (group != null) {
            students = studentService.findAllByGroupId(group.getId());
        }
        model.addAttribute("group", group);
        model.addAttribute("students", students);
        return "list/group_list";
    }
}
