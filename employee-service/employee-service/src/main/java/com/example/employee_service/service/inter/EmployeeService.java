package com.example.employee_service.service.inter;

import com.example.employee_service.dto.ApiDto;
import com.example.employee_service.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeByEmail(String email);
    ApiDto getEmployeeById(Long id);



}
