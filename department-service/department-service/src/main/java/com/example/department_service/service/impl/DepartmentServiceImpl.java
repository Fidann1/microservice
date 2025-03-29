package com.example.department_service.service.impl;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.entity.DepartmentEntity;
import com.example.department_service.mapper.DepartmentMapper;
import com.example.department_service.repository.DepartmentRepository;
import com.example.department_service.service.inter.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        DepartmentEntity savedDepartment=DepartmentMapper.DEPARTMENT_MAPPER.mapToEntity(departmentDto);
        departmentRepository.save(savedDepartment);
        return DepartmentMapper.DEPARTMENT_MAPPER.mapToDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        return DepartmentMapper.DEPARTMENT_MAPPER.mapToDto(departmentRepository.findByDepartmentCode(departmentCode));
    }
}
