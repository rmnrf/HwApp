package com.netcracker.hwapp.security;

import com.netcracker.hwapp.model.Developer;
import com.netcracker.hwapp.repository.DeveloperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final DeveloperRepo developerRepo;

    @Autowired
    public UserDetailsServiceImpl(DeveloperRepo developerRepo) {
        this.developerRepo = developerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Developer developer = developerRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(developer);
    }
}
