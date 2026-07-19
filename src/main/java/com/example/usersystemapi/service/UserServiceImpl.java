package com.example.usersystemapi.service;


import com.example.usersystemapi.dto.UserRequestDto;
import com.example.usersystemapi.dto.UserResponseDto;
import com.example.usersystemapi.entity.User;
import com.example.usersystemapi.exception.DuplicateUserException;
import com.example.usersystemapi.exception.NotFoundException;
import com.example.usersystemapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordencoder;


    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto request){

        userRepository.findByPhoneNumber(request.getPhoneNumber()).ifPresent(
                existing ->{
                    throw new DuplicateUserException(
                            "User with number "+ request.getPhoneNumber() + "already exists"
                    );
                }
        );

        userRepository.findByUsername(request.getUsername()).ifPresent(
                existing ->{
                    throw new DuplicateUserException(
                            "User with username " + request.getUsername() + "already exists"
                    );
                }
        );

        User user = User.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .age(request.getAge())
                .department(request.getDepartment())
                .username(request.getUsername())
                .password(passwordencoder.encode(request.getPassword()))
                .role(request.getRole())
                .active(request.getActive())
                .build();
        User saved = userRepository.save(user);
        return toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getById(Long id){
        User user = findOrThrow(id);
        return toResponseDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers(){
        return userRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> searchByDepartment(String department){
        return userRepository.findByDepartment(department).stream()
                .map(this::toResponseDto)
                .toList();

    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getYoungerUsers(int threshold){
        return userRepository.findByAgeYoungerThan(threshold).stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public void deleteUser(Long id){
        User user = findOrThrow(id);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto request){
        User user = findOrThrow(id);

        if (!user.getPhoneNumber().equals(request.getPhoneNumber())){
            userRepository.findByPhoneNumber(request.getPhoneNumber()).ifPresent(
                    existing -> {
                        throw new DuplicateUserException(
                                "User with number "+ request.getPhoneNumber() + "already exists"
                        );
                    }
            );
        }
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setDepartment(request.getDepartment());
        user.setUsername(request.getUsername());
        user.setPassword(passwordencoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setActive(request.getActive());
        User updated = userRepository.save(user);
        return toResponseDto(updated);
    }



    private User findOrThrow(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("can not found with id:" + id));
    }

    private UserResponseDto toResponseDto(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .age(user.getAge())
                .department(user.getDepartment())
                .username(user.getUsername())
                .role(user.getRole())
                .active(user.getActive())
                .build();
    }



}
