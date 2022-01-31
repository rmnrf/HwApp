package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.exception.TaskNotFoundException;
import com.netcracker.hwapp.model.Task;
import com.netcracker.hwapp.repository.TaskRepo;
import com.netcracker.hwapp.repository.TeacherRepo;
import com.netcracker.hwapp.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping
    public String showTasksPage(Model model, Principal principal) throws TaskNotFoundException {
        List<Task> tasks = null;
        if (principal != null) {
            tasks = taskRepo.findAllByTeacherEmail(principal.getName());
        } else {
            throw new TaskNotFoundException("Задание не найдено");
        }
        model.addAttribute("tasks", tasks);
        return "/list/tasks_list";
    }

    @GetMapping("/new")
    public String showNewTaskForm(Model model, Principal principal) {
        model.addAttribute("faculties", facultyService.findAll());
        model.addAttribute("teacher", teacherRepo.findByEmail(principal.getName()));
        return "/task/new_task";
    }
}
