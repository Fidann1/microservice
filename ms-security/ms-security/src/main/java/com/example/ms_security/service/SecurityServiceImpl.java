package com.example.ms_security.service;

import com.example.ms_security.client.EmployeeClient;
import com.example.ms_security.dto.AuthRequest;
import com.example.ms_security.dto.EmployeeDto;
import com.example.ms_security.exception.CustomFeignException;
import com.example.ms_security.exception.UserAlreadyExistException;
import com.example.ms_security.service.inter.SecurityService;
import com.example.ms_security.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private JwtUtil jwtUtil;
    private EmployeeClient employeeClient;
    private final PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Override
    public String register(EmployeeDto employeeDto) throws Exception {
        System.out.println("Inside service");
        if(employeeClient.getEmployeeDtoByEmail(employeeDto.getEmail())!=null){
            System.out.println("2");
            throw new UserAlreadyExistException("User already exist");
        }
        String encodedPassword;
        System.out.println("3");
        if(employeeDto.getPassword()!=null && !employeeDto.getPassword().isEmpty()){
            encodedPassword=passwordEncoder.encode(employeeDto.getPassword());
        }
        else{
            encodedPassword=null;
        }
        System.out.println("4");
        employeeClient.saveEmployee(EmployeeDto.builder()
                        .id(employeeDto.getId())
                        .departmentCode(employeeDto.getDepartmentCode())
                        .email(employeeDto.getEmail())
                        .firstName(employeeDto.getFirstName())
                        .lastName(employeeDto.getLastName())
                        .password(encodedPassword)
                        .role(employeeDto.getRole())

                .build()

        );
            return "User registered!";

    }

    @Override
    public String login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtUtil.generateToken(authentication);
        }
        else {

         throw new UsernameNotFoundException("User not found!");
        }
    }
}
