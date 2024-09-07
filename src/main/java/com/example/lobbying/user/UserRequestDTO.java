package com.example.lobbying.user;

public record UserRequestDTO(String name, String surname, String email, String password, Integer role) {
}
