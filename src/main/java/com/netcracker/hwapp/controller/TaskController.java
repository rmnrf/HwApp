package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.dto.TaskCreateDto;
import com.netcracker.hwapp.dto.TaskUpdateDto;
import com.netcracker.hwapp.dto.TeacherRegistrationDto;
import com.netcracker.hwapp.exception.TaskNotFoundException;
import com.netcracker.hwapp.model.Discipline;
import com.netcracker.hwapp.model.Task;
import com.netcracker.hwapp.model.Teacher;
import com.netcracker.hwapp.model.User;
import com.netcracker.hwapp.repository.*;
import com.netcracker.hwapp.service.FacultyService;
import com.netcracker.hwapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private TaskRepo taskRepo;

    @ModelAttribute("new_task")
    public TaskCreateDto taskCreateDto() {
        return new TaskCreateDto();
    }

    @GetMapping
    public String showTasksPage(Model model, Principal principal) throws TaskNotFoundException {
        User user = userRepo.findByEmail(principal.getName());
        List<Task> tasks = null;
        switch (user.getUserType()) {
            case "Студент":
                tasks = taskRepo.findAllByGroups_Id(studentRepo.findById(user.getId()).get().getGroup().getId());
                break;
            case "Преподаватель":
                tasks = taskRepo.findAllByTeacherEmail(principal.getName());
                break;
            default:
                throw new TaskNotFoundException("Задания не найдены.");
        }
        model.addAttribute("tasks", tasks);
        return "/list/tasks_list";
    }

    @PreAuthorize("hasAuthority('teacher:perms')")
    @GetMapping("/new")
    public String showNewTaskForm(
            Model model,
            Principal principal) {
        model.addAttribute("faculties", facultyService.findAll());
        model.addAttribute("teacher", teacherRepo.findByEmail(principal.getName()));
        return "/task/new_task";
    }

    @PreAuthorize("hasAuthority('teacher:perms')")
    @PostMapping("/new")
    public String createNewTask(
            @ModelAttribute("new_task")
            @Valid TaskCreateDto taskCreateDto,
            Principal principal) {
        try {
            taskService.create(taskCreateDto, principal);
            return "redirect:/tasks?success";
        } catch (Exception e) {
            return "redirect:/tasks?error";
        }
    }

    @GetMapping("/{id}")
    public String showTaskForm(@PathVariable Long id, Model model) {
        Task task = taskRepo.findById(id).get();
        model.addAttribute("task", task);
        return "/task/show_task";
    }

    @PreAuthorize("hasAuthority('teacher:perms')")
    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable Long id, Model model, Principal principal) {
        Task task = taskRepo.findById(id).get();
        if (!teacherRepo.findByEmail(principal.getName()).getTasks().contains(task)) {
            return "redirect:/tasks?error";
        }
        model.addAttribute("task", task);
        return "/task/edit_task";
    }

    @PreAuthorize("hasAuthority('teacher:perms')")
    @PostMapping("/edit")
    public String updateTask(
            @ModelAttribute("task")
            @Valid TaskUpdateDto taskUpdateDto,
            Principal principal) {
        try {
            taskService.update(taskUpdateDto, principal);
            return "redirect:/tasks?updated";
        } catch (Exception e) {
            return "redirect:/tasks?error";
        }
    }

    @PreAuthorize("hasAuthority('teacher:perms')")
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, Principal principal) {
        try {
            taskService.delete(id, principal);
            return "redirect:/tasks?deleted";
        } catch (Exception e) {
            return "redirect:/tasks?error";
        }
    }
}
