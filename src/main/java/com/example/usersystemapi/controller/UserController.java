package com.example.usersystemapi.controller;

import com.example.usersystemapi.dto.UserRequestDto;
import com.example.usersystemapi.dto.UserResponseDto;
import com.example.usersystemapi.entity.User;
import com.example.usersystemapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserResponseDto> createNewUser(@Valid @RequestBody UserRequestDto request){
        UserResponseDto created = userService.createUser(request);
        URI location = URI.create("/api/users/"+created.getId());
        return ResponseEntity.created(location).body(created);
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id){
        UserResponseDto user = userService.getById(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> user = userService.getAllUsers();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDto>> searchByDepartment(@RequestParam String department){
        List<UserResponseDto> user = userService.searchByDepartment(department);
        return ResponseEntity.ok(user);
    }



    //younger user
    @GetMapping("/age")
    public ResponseEntity<List<UserResponseDto>> getYoungerUsers(@RequestParam int threshold){
        List<UserResponseDto> searchingResult = userService.getYoungerUsers(threshold);
        return ResponseEntity.ok(searchingResult);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto request){
        UserResponseDto updated = userService.updateUser(id,request);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }










}
