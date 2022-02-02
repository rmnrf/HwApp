package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.model.Student;
import com.netcracker.hwapp.model.Teacher;
import com.netcracker.hwapp.repository.FacultyRepo;
import com.netcracker.hwapp.repository.GroupRepo;
import com.netcracker.hwapp.repository.StudentRepo;
import com.netcracker.hwapp.repository.TeacherRepo;
import com.netcracker.hwapp.service.DisciplineService;
import com.netcracker.hwapp.service.StudentService;
import com.netcracker.hwapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentsListController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private FacultyRepo facultyRepo;

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/students")
    public String showStudentsPage(@RequestParam(value="groupNumber", required = false) Integer groupNumber,
                                   @RequestParam(value="facultyName", required = false) String facultyName,
                                   Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("students", students);
        model.addAttribute("faculties", facultyRepo.findAll());
        return "list/students_list";
    }
}

