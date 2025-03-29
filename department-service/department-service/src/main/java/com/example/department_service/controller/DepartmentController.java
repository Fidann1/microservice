package com.example.department_service.controller;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.service.inter.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
@AllArgsConstructor
public class DepartmentController {

     private DepartmentService departmentService;

     @PostMapping()
    private ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto saveDepartment=  departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
     }

     @GetMapping("/departmentCode/{departmentCode}")
    private ResponseEntity<DepartmentDto> getDepartment(@PathVariable String departmentCode){
         DepartmentDto getDepartment=departmentService.getDepartmentByCode(departmentCode);
       return new ResponseEntity<>(getDepartment,HttpStatus.OK);
     }
}
