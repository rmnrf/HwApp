package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.*;
import com.netcracker.hwapp.exception.DeadlineDateTimeHasExriped;
import com.netcracker.hwapp.exception.SolutionAlreadyExistsException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

public interface SolutionService {
    void create(SolutionCreateDto dto, Principal principal, MultipartFile file)
            throws SolutionAlreadyExistsException, DeadlineDateTimeHasExriped, IOException;
    void update(SolutionUpdateDto dto, Principal principal);
    void delete(Long id, Principal principal);
    void estimate(SolutionEstimateDto dto, Principal principal);
}
