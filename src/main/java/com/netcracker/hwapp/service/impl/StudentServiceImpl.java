package com.netcracker.hwapp.service.impl;

import com.netcracker.hwapp.exception.UserNotFoundException;
import com.netcracker.hwapp.model.Solution;
import com.netcracker.hwapp.model.Student;
import com.netcracker.hwapp.model.Task;
import com.netcracker.hwapp.repository.SolutionRepo;
import com.netcracker.hwapp.repository.StudentRepo;
import com.netcracker.hwapp.repository.TaskRepo;
import com.netcracker.hwapp.service.StudentService;
import com.netcracker.hwapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SolutionRepo solutionRepo;

    @Override
    public Student findByEmail(String email) throws UserNotFoundException {
        Optional<Student> optional = studentRepo.findByEmail(email);
        if (optional.isEmpty()) {
            throw new UserNotFoundException("Такого пользователя не существует.");
        }
        return studentRepo.findByEmail(email).get();
    }

    @Override
    public List<Student> findAllByGroupId(Long id) {
        return studentRepo.findAllByGroupId(id);
    }

    @Override
    public List<Task> findAllUnresolvedTasks(Long studentId) {
        Student student = studentRepo.findById(studentId).get();
        List<Task> groupTasks = taskRepo.findAllByGroups_Id(student.getGroup().getId());
        List<Solution> studentSolutions = solutionRepo.findAllByStudentEmail(student.getEmail());
        List<Task> resolvedTasks = studentSolutions.stream().map(Solution::getTask).collect(Collectors.toList());
        groupTasks.removeAll(resolvedTasks);
        return groupTasks;
    }

    @Override
    public List<Task> findAllActualTasks(Long studentId) {
        Student student = studentRepo.findById(studentId).get();
        List<Task> tasksNotOverdue = taskService.findAllNotOverdueTasksByGroupId(student.getGroup().getId());
        List<Task> tasksUnresolved = findAllUnresolvedTasks(studentId);
        tasksUnresolved.retainAll(tasksNotOverdue);
        return tasksUnresolved;
    }


}
