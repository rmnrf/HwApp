package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.model.Teacher;
import com.netcracker.hwapp.repository.TeacherRepo;
import com.netcracker.hwapp.service.DisciplineService;
import com.netcracker.hwapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeachersListController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DisciplineService disciplineService;

    @Autowired
    private TeacherRepo teacherRepo;

    @GetMapping("/teachers")
    public String showTeachersPage(@RequestParam(value="disciplineId", required = false) Long disciplineId,
                                   Model model) {
        List<Teacher> teachers = null;
        if (disciplineId != null) {
            teachers = teacherRepo.findByDisciplines_Id(disciplineId);
        } else {
            teachers = teacherService.findAll();
        }
        model.addAttribute("disciplines", disciplineService.findAll());
        model.addAttribute("teachers", teachers);
        return "list/teachers_list";
    }
}
