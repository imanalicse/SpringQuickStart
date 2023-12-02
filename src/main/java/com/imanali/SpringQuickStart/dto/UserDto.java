package com.imanali.SpringQuickStart.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required") @Email(message = "Invalid email address")
    private String email;

    @NotNull
    @Pattern(regexp = "\\+-[0-9]", message = "Invalid mobile number")
    private String mobile;
    private String gender;

    @Min(value = 18, message = "Age should be greater than or equal 18")
    @Max(value = 60, message = "Age should be less than or equal 60")
    private int age;

    @NotBlank
    private String nationality;
}
