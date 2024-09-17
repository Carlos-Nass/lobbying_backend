package com.example.lobbying.user;

import com.example.lobbying.personalityTest.PersonalityTrait;
import com.example.lobbying.personalityTest.PersonalityTraitModel;
import com.example.lobbying.tag.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private String email;
    private String password;
    private Integer role;

    @Enumerated
    @Column(name = "personality_trait", nullable = true)
    private PersonalityTrait personalityTrait;

    @ManyToMany
    @JoinTable(name = "users_tags",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_tags"))
    List<Tag> tags;

}
