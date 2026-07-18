package com.example.usersystemapi.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserRequestDto {
    @NotBlank(message = "Name is required")
    @Size(max = 200, message = "Name is too long")
    private String name;

    @NotBlank(message = "PhoneNumber is required")
    @Size(max = 20, message = "Phone number is too long")
    private String phoneNumber;

    @NotNull(message = "Age is required")
    @Min(value =0 , message = "Age can not be negative")
    private Integer age;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "username is required")
    @Size(max=40, message = "username can not over 50 characters")
    private String username;

    @NotBlank(message = "pwd is required")
    @Size(max=100, message = "pwd can not exceed 100 characters")
    private String password;


    @NotNull(message = "Active status is required")
    private String active;

    @Pattern(regexp="USER|ADMIN",message = "Role must be USER or ADMIN")
    private String role;


}