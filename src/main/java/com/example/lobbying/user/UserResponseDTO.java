package com.example.lobbying.user;

public record UserResponseDTO(Long id, String name, String surname, String password, Integer role) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getName(), user.getSurname(), user.getPassword(), user.getRole());
    }
}

