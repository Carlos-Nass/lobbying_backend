package com.example.lobbying.vacancy;

public record VacancyResponseDTO(Long id, String title, String description) {
    public VacancyResponseDTO(Vacancy vacancy){
        this(vacancy.getId(), vacancy.getTitle(), vacancy.getDescription());
    }
}
