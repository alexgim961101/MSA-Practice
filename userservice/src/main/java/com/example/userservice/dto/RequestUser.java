package com.example.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUser {
    @NotNull
    @Size(min = 2)
    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 15)
    private String pwd;

    @NotNull
    @Size(min = 2)
    private String name;
}
