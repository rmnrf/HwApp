package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.Faculty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TaskCreateDto {

    @NotNull(message = "Укажите факультет")
    private Faculty faculty;

    private Integer groupNumber;

    private Long disciplineId;

    @Size(min = 1, max = 1000, message = "Длина текста должна находиться в диапазоне от 1 до 1000 символов")
    @NotBlank(message = "Необходимо указать текст задания")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadlineDateTime;
}
