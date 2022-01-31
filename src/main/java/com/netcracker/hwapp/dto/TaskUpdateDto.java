package com.netcracker.hwapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TaskUpdateDto {

    private Long id;

    @Size(min = 1, max = 1000, message = "Длина текста должна находиться в диапазоне от 1 до 1000 символов")
    @NotBlank(message = "Необходимо указать текст задания")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadlineDateTime;

}
