package com.example.lobbying.vacancy.dto;

import com.example.lobbying.generic.GenericDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDTO {

    private Long id;
    private String name;

    private List<GenericDTO> tags;

}
