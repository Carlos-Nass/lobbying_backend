package com.example.lobbying.user;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String password;
    private Integer role;

    public User(UserRequestDTO data){
        this.name = data.name();
        this.surname = data.surname();
        this.password = data.password();
        this.role = data.role();
    }
}
