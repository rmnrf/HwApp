package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.File;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SolutionUpdateDto {

    private Long id;

    private File file;

    @Size(max = 1000, message = "Длина текста должна быть не более 1000 символов")
    private String comment;

}
