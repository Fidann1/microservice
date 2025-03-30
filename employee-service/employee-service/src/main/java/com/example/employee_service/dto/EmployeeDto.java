package com.example.employee_service.dto;

import com.example.employee_service.enums.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    @Email (message="Invalid email address")
    private String email;
    private String departmentCode;
    @NotBlank(message="password should not be null")
    private String password;

    private RoleEnum role;
}
