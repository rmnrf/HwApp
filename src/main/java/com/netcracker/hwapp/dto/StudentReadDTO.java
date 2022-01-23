package com.netcracker.hwapp.dto;

import com.netcracker.hwapp.model.Faculty;
import com.netcracker.hwapp.model.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentReadDTO extends UserReadDTO {
    private Group group;
}
