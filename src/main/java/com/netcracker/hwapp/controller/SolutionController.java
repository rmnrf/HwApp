package com.netcracker.hwapp.controller;

import com.netcracker.hwapp.dto.*;
import com.netcracker.hwapp.exception.SolutionNotFoundException;
import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.*;
import com.netcracker.hwapp.model.File;
import com.netcracker.hwapp.repository.FileRepo;
import com.netcracker.hwapp.repository.SolutionRepo;
import com.netcracker.hwapp.repository.TaskRepo;
import com.netcracker.hwapp.repository.UserRepo;
import com.netcracker.hwapp.service.SolutionService;
import com.netcracker.hwapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLConnection;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/solutions")
public class SolutionController {

    @Autowired
    private FileRepo fileRepo;

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
    public String showSolutionsPage(@RequestParam(required = false) Long taskId,
                                    Model model,
                                    Principal principal)
            throws SolutionNotFoundException {
        User user = userRepo.findByEmail(principal.getName());
        List<Solution> solutions = null;
        switch (user.getUserType()) {
            case "Студент":
                solutions = solutionRepo.findAllByStudentEmail(principal.getName());
                break;
            case "Преподаватель":
                if (taskId == null) {
                    solutions = solutionRepo.findAllByTaskTeacherId(user.getId());
                } else {
                    solutions = solutionRepo.findAllByTaskId(taskId);
                }
                break;
            default:
                throw new SolutionNotFoundException("Решения не найдены.");
        }
        model.addAttribute("solutions", solutions);
        return "list/solutions_list";
    }

    @PreAuthorize("hasAuthority('student:perms')")
    @GetMapping("/new")
    public String showNewSolutionForm(
            Model model,
            Principal principal) throws UserNotFoundException {
        Student student = studentService.findByEmail(principal.getName());
        model.addAttribute("tasks", studentService.findAllActualTasks(student.getId()));
        return "solution/new_solution";
    }

    @PreAuthorize("hasAuthority('student:perms')")
    @PostMapping("/new")
    public String createNewSolution(
            @ModelAttribute("new_solution")
            @Valid SolutionCreateDto solutionCreateDto,
            @RequestParam("myFile") MultipartFile myFile,
            Principal principal) {
        try {
            solutionService.create(solutionCreateDto, principal, myFile);
            return "redirect:/solutions?success";
        } catch (Exception e) {
            return "redirect:/solutions?error";
        }
    }

    @PreAuthorize("hasAuthority('student:perms')")
    @GetMapping("/{id}")
    public String showSolutionForm(@PathVariable Long id, Model model) {
        Solution solution = solutionRepo.findById(id).get();
        model.addAttribute("solution", solution);
        return "solution/show_solution";
    }

    @PreAuthorize("hasAuthority('student:perms')")
    @GetMapping("/edit/{id}")
    public String showEditSolutionForm(@PathVariable Long id, Model model, Principal principal) {
        Solution solution = solutionRepo.findById(id).get();
        if (!solutionRepo.findAllByStudentEmail(principal.getName()).contains(solution)) {
            return "redirect:/solutions?error";
        }
        model.addAttribute("solution", solution);
        return "solution/edit_solution";
    }

    @PreAuthorize("hasAuthority('student:perms')")
    @PostMapping("/edit")
    public String updateSolution(
            @ModelAttribute("solution")
            @Valid SolutionUpdateDto solutionUpdateDto,
            @RequestParam("myFile") MultipartFile myFile,
            Principal principal) {
        try {
            solutionService.update(solutionUpdateDto, principal, myFile);
            return "redirect:/solutions?updated";
        } catch (Exception e) {
            return "redirect:/solutions?error";
        }
    }

    @PreAuthorize("hasAuthority('student:perms')")
    @GetMapping("/delete/{id}")
    public String deleteSolution(@PathVariable Long id, Principal principal) {
        try {
            solutionService.delete(id, principal);
            return "redirect:/solutions?deleted";
        } catch (Exception e) {
            return "redirect:/solutions?error";
        }
    }

    @PreAuthorize("hasAuthority('teacher:perms')")
    @GetMapping("/estimate/{id}")
    public String showEstimateSolutionForm(@PathVariable Long id, Model model, Principal principal) {
        Solution solution = solutionRepo.findById(id).get();
        if (!solutionRepo.findAllByTaskTeacherEmail(principal.getName()).contains(solution)) {
            return "redirect:/solutions?error";
        }
        model.addAttribute("solution", solution);
        return "solution/estimate_solution";
    }

    @PreAuthorize("hasAuthority('teacher:perms')")
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

    @GetMapping("{id}/files/{fileName}")
    public void getFile(@PathVariable("id") Long id,
                        @PathVariable("fileName") String fileName,
                        HttpServletResponse response) throws Exception {
        Optional<File> file = fileRepo.findByName(fileName);
        if (file.isPresent()) {
            byte[] bytes = file.get().getData();
            InputStream is = new BufferedInputStream(new ByteArrayInputStream(bytes));
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            response.setContentType(mimeType);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        }
    }
}
