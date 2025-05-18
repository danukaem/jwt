package com.assignment.eventManagementSystem.dto;

import com.assignment.eventManagementSystem.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewUserDTO {
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    private Role role;
    private String password;

}
