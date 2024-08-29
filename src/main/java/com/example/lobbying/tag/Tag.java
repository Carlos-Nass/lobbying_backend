package com.example.lobbying.tag;

import com.example.lobbying.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "tags")
@Entity(name = "tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "tags_users",
            joinColumns = @JoinColumn(name = "id_tag"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    List<User> users;

}
