package com.example.ms_security.dto;

import com.example.ms_security.enums.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
   // @Email(message = "Not valid email format")
    private String email;
   // @NotBlank(message= "Password can not be blank")
    private String password;


    private RoleEnum role;
}
