package com.example.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestLogin {

    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two char")
    @Email
    private String email;

    @NotNull(message = "Pwd Cannot be null")
    @Size(min = 8, message = "Pwd must be equals or grater than 8 char")
    private String pwd;
}
