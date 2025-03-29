package com.example.ms_security.controller;


import com.example.ms_security.dto.AuthRequest;
import com.example.ms_security.dto.EmployeeDto;
import com.example.ms_security.service.inter.SecurityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final SecurityService securityService;

    @PostMapping("/register")
    public String register(@RequestBody EmployeeDto employeeDto) throws Exception {
        System.out.println("Register");
        return securityService.register(employeeDto);
    }

    @GetMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) throws Exception {
        return securityService.login(authRequest);
    }

}
