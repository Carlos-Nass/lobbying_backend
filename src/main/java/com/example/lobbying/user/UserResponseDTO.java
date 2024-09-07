package com.example.lobbying.user;

public record UserResponseDTO(Long id, String name, String surname, String email, String password, Integer role, String token) {

    public UserResponseDTO(User user, String token){
        this(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getRole(), token);
    }

}

