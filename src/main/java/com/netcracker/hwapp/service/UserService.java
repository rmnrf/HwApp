package com.netcracker.hwapp.service;

import com.netcracker.hwapp.dto.UserRegistrationDto;
import com.netcracker.hwapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
