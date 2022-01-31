package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.Faculty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TaskCreateDto {

    @NotNull(message = "Укажите факультет")
    private Faculty faculty;

    private Integer groupNumber;

    private Long disciplineId;

    @NotBlank(message = "Необходимо указать текст задания")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadlineDateTime;
}
