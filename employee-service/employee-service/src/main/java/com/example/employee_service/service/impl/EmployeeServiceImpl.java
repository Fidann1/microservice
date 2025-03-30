package com.example.employee_service.service.impl;

import com.example.employee_service.client.APIClient;
import com.example.employee_service.dto.ApiDto;
import com.example.employee_service.dto.DepartmentDto;
import com.example.employee_service.dto.EmployeeDto;
import com.example.employee_service.dto.ErrorResponse;
import com.example.employee_service.entity.EmployeeEntity;
import com.example.employee_service.mapper.EmployeeMapper;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.service.inter.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private APIClient apiClient;


    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        if(apiClient.getDepartment(employeeDto.getDepartmentCode())!=null){EmployeeEntity savedEmployee= EmployeeMapper.EMPLOYEE_MAPPER.mapToEmployeeEntity(employeeDto);
            employeeRepository.save(savedEmployee);
            return EmployeeMapper.EMPLOYEE_MAPPER.mapToEmployeeDto(savedEmployee);}
        return null;
    }

    @Override
    public EmployeeDto getEmployeeByEmail(String email) {
        System.out.println("employee service");
        EmployeeEntity employeeEntity = employeeRepository.findByEmail(email).orElse(null);
        if(employeeEntity != null) {return EmployeeMapper.EMPLOYEE_MAPPER.mapToEmployeeDto(employeeEntity);}
        else{return null;}

    }

    @Override
    public ApiDto getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        DepartmentDto departmentDto = apiClient.getDepartment(employeeEntity.getDepartmentCode());
        ApiDto api=ApiDto.builder()
                .departmentDto(departmentDto)
                .employeeDto(EmployeeMapper.EMPLOYEE_MAPPER.mapToEmployeeDto(employeeEntity))
                .build();
        return api ;
    }
}
