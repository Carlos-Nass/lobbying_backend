package com.example.lobbying.tag;

import jakarta.persistence.*;
import lombok.*;

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

}
