package com.example.employee_service.entity;

import com.example.employee_service.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
    private String departmentCode;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
