package com.imanali.SpringQuickStart.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imanali.SpringQuickStart.model.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @JsonProperty("confirm_password")
    private String confirmPassword;
    private Role role;
}
