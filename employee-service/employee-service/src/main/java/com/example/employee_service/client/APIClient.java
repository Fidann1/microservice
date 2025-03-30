package com.example.employee_service.client;


import com.example.employee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="DEPARTMENT-SERVICE",
        configuration = CustomErrorDecoder.class
)
public interface APIClient {
    @GetMapping("/api/department/departmentCode/{departmentCode}")
     DepartmentDto getDepartment(@PathVariable String departmentCode);

}
