package com.example.lobbying.personalityTest;

import com.example.lobbying.tag.Tag;
import com.example.lobbying.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "personality_trait")
public class PersonalityTraitModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    @Column(name = "personality_trait", nullable = true)
    private PersonalityTrait personalityTrait;

    @OneToMany(mappedBy = "personalityTrait", cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

}
