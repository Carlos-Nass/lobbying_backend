package com.example.lobbying.vacancy;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "vacancy")
@Entity(name = "vacancy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Vacancy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    public Vacancy(VacancyRequestDTO data){
        this.title = data.title();
        this.description = data.title();
    }

}
