package com.example.lobbying.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    public User createUser(UserRequestDTO data){

        User newUser = new User();

        newUser.setName(data.name());
        newUser.setSurname(data.surname());
        newUser.setEmail(data.email());
        newUser.setPassword(passwordEncoder.encode(data.password()));
        newUser.setRole(0);

        repository.save(newUser);

        return  newUser;
    }

    public List<User> getUsers(){

        return repository.findAll();
    }

    public void deleteUser(Long id){

        this.repository.delete(this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found")));
    }

    public User updateUser(Long id, User user){
        User savedUser = repository.findById(id).orElseThrow(() -> new RuntimeException("Timeout"));
        savedUser.setName(user.getName());
        savedUser.setSurname(user.getSurname());
        savedUser.setEmail(user.getEmail());
        savedUser.setPassword(user.getPassword());

        repository.save(savedUser);
        return savedUser;
    }
}
