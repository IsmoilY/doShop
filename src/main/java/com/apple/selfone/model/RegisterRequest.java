package com.apple.selfone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Size(min = 2, max = 32, message = "Username is mandatory")
    private String username;

    @Email
    @NotBlank
    private String email;

    @Size(min = 2, max = 128, message = "Full name is required")
    private String fullName;

    @Size(min = 8, message = "Password length is 8")
    private String prePassword;

    @Size(min = 8, message = "Minimum length is 8")
    private String password;

    @AssertTrue(message = "Password is not confirmed")
    private boolean isValid(){
        return this.password.equals(this.prePassword);
    }

}
