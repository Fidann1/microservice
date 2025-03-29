package com.example.employee_service.mapper;

import com.example.employee_service.dto.EmployeeDto;
import com.example.employee_service.entity.EmployeeEntity;

public enum EmployeeMapper {
    EMPLOYEE_MAPPER;

    public EmployeeEntity mapToEmployeeEntity(EmployeeDto employeeDto) {
        return EmployeeEntity.builder()
                .id(employeeDto.getId())
                .email(employeeDto.getEmail())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .departmentCode(employeeDto.getDepartmentCode())
                .password(employeeDto.getPassword())
                .role(employeeDto.getRole())
                .build();
    }

   public  EmployeeDto mapToEmployeeDto(EmployeeEntity employeeEntity) {
        return EmployeeDto.builder()
                .id(employeeEntity.getId())
                .email(employeeEntity.getEmail())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .departmentCode(employeeEntity.getDepartmentCode())
                .password(employeeEntity.getPassword())
                .role(employeeEntity.getRole())
                .build();
    }
}
