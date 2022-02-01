package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.*;
import com.netcracker.hwapp.exception.DeadlineDateTimeHasExriped;
import com.netcracker.hwapp.exception.SolutionAlreadyExistsException;

import java.security.Principal;

public interface SolutionService {
    void create(SolutionCreateDto dto, Principal principal) throws SolutionAlreadyExistsException, DeadlineDateTimeHasExriped;
    void update(SolutionUpdateDto dto, Principal principal);
    void delete(Long id, Principal principal);
    void estimate(SolutionEstimateDto dto, Principal principal);
}
