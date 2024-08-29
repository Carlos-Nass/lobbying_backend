package com.example.lobbying.vacancy.dto;

import com.example.lobbying.generic.GenericDTO;
import com.example.lobbying.vacancy.Vacancy;

import java.util.List;

public record VacancyResponseDTO(Long id, String title, String description, List<GenericDTO> tags)  {
    public VacancyResponseDTO(Vacancy vacancy){
        this(vacancy.getId(), vacancy.getTitle(), vacancy.getDescription(), vacancy.getTags().stream()
                .map(tag -> new GenericDTO(tag.getId(), tag.getName())).toList());
    }
}
