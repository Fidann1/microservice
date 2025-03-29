package com.example.employee_service.repository;

import com.example.employee_service.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository  extends JpaRepository<EmployeeEntity,Long> {

    Optional<EmployeeEntity> findByEmail(String email);
}
