package com.example.ms_security.service;

import com.example.ms_security.client.EmployeeClient;
import com.example.ms_security.dto.CustomUserDetails;
import com.example.ms_security.dto.EmployeeDto;
import com.example.ms_security.dto.EmployeeResponse;
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
        if(employeeDto==null){
            throw new UsernameNotFoundException(email);
        }
        EmployeeResponse employeeResponse=EmployeeResponse.builder()
                .role(String.valueOf(employeeDto.getRole()))
                .email(employeeDto.getEmail())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .password(employeeDto.getPassword())
                .id(employeeDto.getId())
                .departmentCode(employeeDto.getDepartmentCode())
                .build();
        return new CustomUserDetails(employeeResponse);
    }
}
