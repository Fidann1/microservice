package com.example.employee_service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiDto {
    EmployeeDto employeeDto;
    DepartmentDto departmentDto;
}
