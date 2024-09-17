package com.example.lobbying.tag;

import com.example.lobbying.personalityTest.PersonalityTraitModel;
import com.example.lobbying.user.User;
import com.example.lobbying.vacancy.Vacancy;
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

    @ManyToMany(mappedBy = "tags")
    List<User> users;

    @ManyToMany(mappedBy = "tags")
    List<Vacancy> vacancies;

    @ManyToOne
    @JoinColumn(name = "personality_trait_id")
    private PersonalityTraitModel personalityTrait;

}
