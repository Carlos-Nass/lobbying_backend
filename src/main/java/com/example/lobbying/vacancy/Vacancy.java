package com.example.lobbying.vacancy;

import com.example.lobbying.tag.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "vacancy")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToMany
    @JoinTable(name = "vacancy_tags",
            joinColumns = @JoinColumn(name = "id_vacancy"),
            inverseJoinColumns = @JoinColumn(name = "id_tags"))
    List<Tag> tags;

}
