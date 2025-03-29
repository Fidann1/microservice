package com.example.ms_security.service;

import com.example.ms_security.client.EmployeeClient;
import com.example.ms_security.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    EmployeeClient employeeClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EmployeeDto employeeDto=employeeClient.getEmployeeDtoByEmail(email);
        return new User(employeeDto.getEmail(),employeeDto.getPassword(),new ArrayList<>());
    }
}
