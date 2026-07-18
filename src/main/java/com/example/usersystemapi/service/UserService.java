package com.example.usersystemapi.service;

import com.example.usersystemapi.dto.UserRequestDto;
import com.example.usersystemapi.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto request);

    UserResponseDto getById(Long id);

    List<UserResponseDto> getAllUsers();

    List<UserResponseDto> searchByDepartment(String department);

    List<UserResponseDto> getYoungerUsers(int threshold);

    void deleteUser(Long id);

    UserResponseDto updateUser(Long id, UserRequestDto request);

    //List<UserResponseDto> searchByName(String name);

    //List<UserResponseDto> searchByNumber(String phoneNumber);



}
