package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.dto.*;
import com.netcracker.hwapp.enums.Grade;
import com.netcracker.hwapp.exception.SolutionNotFoundException;
import com.netcracker.hwapp.exception.TaskNotFoundException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Solution;
import com.netcracker.hwapp.model.Student;
import com.netcracker.hwapp.model.Task;
import com.netcracker.hwapp.model.User;
import com.netcracker.hwapp.repository.SolutionRepo;
import com.netcracker.hwapp.repository.TaskRepo;
import com.netcracker.hwapp.repository.UserRepo;
import com.netcracker.hwapp.service.SolutionService;
import com.netcracker.hwapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/solutions")
public class SolutionController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SolutionRepo solutionRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SolutionService solutionService;

    @ModelAttribute("new_solution")
    public SolutionCreateDto solutionCreateDto() {
        return new SolutionCreateDto();
    }

    @ModelAttribute("estimate_solution")
    public SolutionEstimateDto solutionEstimateDto() {
        return new SolutionEstimateDto();
    }

    @GetMapping
    public String showSolutionsPage(Model model, Principal principal) throws SolutionNotFoundException {
        User user = userRepo.findByEmail(principal.getName());
        List<Solution> solutions = null;
        switch (user.getUserType()) {
            case "Студент":
                solutions = solutionRepo.findAllByStudentEmail(principal.getName());
                break;
            case "Преподаватель":
                solutions = solutionRepo.findAllByTaskTeacherId(user.getId());
                break;
            default:
                throw new SolutionNotFoundException("Решения не найдены.");
        }
        model.addAttribute("solutions", solutions);
        return "/list/solutions_list";
    }

    @GetMapping("/new")
    public String showNewSolutionForm(
            Model model,
            Principal principal) throws UserNotFoundException {
        Student student = studentService.findByEmail(principal.getName());
        model.addAttribute("tasks", taskRepo.findAllByGroups_Id(student.getGroup().getId()));
        return "/solution/new_solution";
    }

    @PostMapping("/new")
    public String createNewSolution(
            @ModelAttribute("new_solution")
            @Valid SolutionCreateDto solutionCreateDto,
            Principal principal) {
        try {
            solutionService.create(solutionCreateDto, principal);
            return "redirect:/solutions?success";
        } catch (Exception e) {
            return "redirect:/solutions?error";
        }
    }

    @GetMapping("/{id}")
    public String showSolutionForm(@PathVariable Long id, Model model) {
        Solution solution = solutionRepo.findById(id).get();
        model.addAttribute("solution", solution);
        return "/solution/show_solution";
    }

    @GetMapping("/edit/{id}")
    public String showEditSolutionForm(@PathVariable Long id, Model model, Principal principal) {
        Solution solution = solutionRepo.findById(id).get();
        if (!solutionRepo.findAllByStudentEmail(principal.getName()).contains(solution)) {
            return "redirect:/solutions?error";
        }
        model.addAttribute("solution", solution);
        return "/solution/edit_solution";
    }

    @PostMapping("/edit")
    public String updateSolution(
            @ModelAttribute("solution")
            @Valid SolutionUpdateDto solutionUpdateDto,
            Principal principal) {
        try {
            solutionService.update(solutionUpdateDto, principal);
            return "redirect:/solutions?updated";
        } catch (Exception e) {
            return "redirect:/solutions?error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, Principal principal) {
        try {
            solutionService.delete(id, principal);
            return "redirect:/solutions?deleted";
        } catch (Exception e) {
            return "redirect:/solutions?error";
        }
    }

    @GetMapping("/estimate/{id}")
    public String showEstimateSolutionForm(@PathVariable Long id, Model model, Principal principal) {
        Solution solution = solutionRepo.findById(id).get();
        if (!solutionRepo.findAllByTaskTeacherEmail(principal.getName()).contains(solution)) {
            return "redirect:/solutions?error";
        }
        model.addAttribute("solution", solution);
        return "/solution/estimate_solution";
    }

    @PostMapping("/estimate")
    public String estimateTask(
            @ModelAttribute("estimate_solution")
            @Valid SolutionEstimateDto solutionEstimateDto,
            Principal principal) {
        try {
            solutionService.estimate(solutionEstimateDto, principal);
            return "redirect:/solutions?estimated";
        } catch (Exception e) {
            return "redirect:/solutions?error";
        }
    }
}
