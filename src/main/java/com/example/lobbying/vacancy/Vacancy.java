package com.example.lobbying.vacancy;

import jakarta.persistence.*;
import lombok.*;

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

}
