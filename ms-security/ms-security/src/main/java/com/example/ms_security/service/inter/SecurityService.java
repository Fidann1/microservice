package com.example.ms_security.service.inter;

import com.example.ms_security.dto.AuthRequest;
import com.example.ms_security.dto.EmployeeDto;

public interface SecurityService {
    public String register(EmployeeDto employeeDto) throws Exception;
    public String login(AuthRequest authRequest);
}
