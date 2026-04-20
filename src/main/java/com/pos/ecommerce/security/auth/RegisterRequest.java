package com.pos.ecommerce.security.auth;

import com.pos.ecommerce.constant.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String firstname;
    @NotBlank private String lastname;
    @Email
    private String email;
    @Size(min = 8) private String password;
    private Role role;
}