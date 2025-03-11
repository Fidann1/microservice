package com.example.department_service.mapper;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.entity.DepartmentEntity;


public enum DepartmentMapper {
    DEPARTMENT_MAPPER;

    public DepartmentEntity mapToEntity(DepartmentDto departmentDto) {
        return DepartmentEntity.builder()
                .departmentName(departmentDto.getDepartmentName())
                .departmentCode(departmentDto.getDepartmentCode())
                .departmentDescription(departmentDto.getDepartmentDescription())
                .id(departmentDto.getId())
                .build();

    }

    public DepartmentDto mapToDto(DepartmentEntity departmentEntity) {
        return DepartmentDto.builder()
                .departmentName(departmentEntity.getDepartmentName())
                .departmentCode(departmentEntity.getDepartmentCode())
                .departmentDescription(departmentEntity.getDepartmentDescription())
                .id(departmentEntity.getId())
                .build();
    }
}
