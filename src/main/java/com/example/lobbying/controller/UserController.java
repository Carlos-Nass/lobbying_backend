package com.example.lobbying.controller;

import com.example.lobbying.infra.security.TokenService;
import com.example.lobbying.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO body){

        this.userService.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User already exists"));

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

        this.userService.findByEmail(user.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        User updateUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserRequestDTO body){
        Optional<User> optionalUser = this.userService.findByEmail(body.email());

        if (optionalUser.isPresent()){

            User user = optionalUser.get();

            if (userService.verifyPassword(body.password(), user.getPassword())){
                String token = this.tokenService.generateToken(user);
                return ResponseEntity.ok(new UserResponseDTO(user, token));
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong password");

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @PutMapping("/auth/updatePassword")
    public ResponseEntity<Object> updatePassword(@RequestBody UserRequestDTO body){

        User user = this.userService.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(userService.updatePassword(user, body.password()));

    }

}
