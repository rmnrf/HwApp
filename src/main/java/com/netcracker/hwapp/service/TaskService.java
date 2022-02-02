package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.TaskCreateDto;
import com.netcracker.hwapp.dto.TaskUpdateDto;
import com.netcracker.hwapp.exception.TaskAlreadyExistsException;
import com.netcracker.hwapp.model.Task;

import java.security.Principal;
import java.util.List;

public interface TaskService {
    Task create(TaskCreateDto dto, Principal principal) throws TaskAlreadyExistsException;
    void update(TaskUpdateDto dto, Principal principal);
    void delete(Long id, Principal principal);
    List<Task> findAllNotOverdueTasksByGroupId(Long groupId);
}
