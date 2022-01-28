package com.netcracker.hwapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisciplineDTO implements DTOEntity {

    private Long id;
    private String name;

}
