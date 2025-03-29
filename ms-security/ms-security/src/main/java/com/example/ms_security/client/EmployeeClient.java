package com.example.ms_security.client;

import com.example.ms_security.dto.EmployeeDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "EMPLOYEE-SERVICE",
        configuration = CustomErrorDecoder.class
)
public interface EmployeeClient {
    @GetMapping("/api/employee/email/{email}")
     EmployeeDto getEmployeeDtoByEmail(@PathVariable String email) ;

    @PostMapping("/api/employee")
     ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) ;

}
