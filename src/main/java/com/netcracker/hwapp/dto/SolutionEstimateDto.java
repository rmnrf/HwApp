package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.enums.Grade;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SolutionEstimateDto {

    private Long id;

    @NotNull
    private Grade grade;

}
