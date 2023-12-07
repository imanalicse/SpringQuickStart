package com.imanali.SpringQuickStart.dto;

import com.imanali.SpringQuickStart.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "Name is required")
    private String firstname;
    private String lastname;

    @NotBlank(message = "Email is required") @Email(message = "Invalid email address")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
