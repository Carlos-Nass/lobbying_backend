package com.example.lobbying.controller;

import com.example.lobbying.user.User;
import com.example.lobbying.user.UserRepository;
import com.example.lobbying.user.UserRequestDTO;
import com.example.lobbying.user.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveUser(@RequestBody UserRequestDTO data){
        User userData = new User(data);
        repository.save(userData);
        return;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<UserResponseDTO> getAll(){

        List<UserResponseDTO> userList = repository.findAll().stream().map(UserResponseDTO::new).toList();
        return userList;
    }
}
