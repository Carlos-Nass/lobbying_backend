package com.example.lobbying.controller;

import com.example.lobbying.infra.security.TokenService;
import com.example.lobbying.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO body){
        User newUser = this.userService.createUser(body);
        String token = this.tokenService.generateToken(newUser);
        return ResponseEntity.ok(new UserResponseDTO(newUser, token));
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User updateUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
