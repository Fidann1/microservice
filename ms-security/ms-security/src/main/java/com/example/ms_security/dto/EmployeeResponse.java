package com.example.ms_security.dto;

import com.example.ms_security.enums.RoleEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentCode;
    private String password;
    private String role;

}
