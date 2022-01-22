package com.netcracker.hwapp.model;

import com.netcracker.hwapp.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private LocalDate creationDate;
    private LocalDate deadlineDate;
    private String description;
    private User creator;

    public static Task toModel(TaskEntity entity) {
        Task model = new Task();
        model.setId(entity.getId());
        model.setCreationDate(entity.getCreationDate());
        model.setDeadlineDate(entity.getDeadlineDate());
        model.setDescription(entity.getDescription());
        //model.setCreator(User.toModel(entity.getCreator()));
        return model;
    }
}
