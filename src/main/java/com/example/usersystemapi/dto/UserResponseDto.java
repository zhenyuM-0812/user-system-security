package com.example.usersystemapi.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;

    private String name;

    private String phoneNumber;

    private Integer age;

    private String department;

}
