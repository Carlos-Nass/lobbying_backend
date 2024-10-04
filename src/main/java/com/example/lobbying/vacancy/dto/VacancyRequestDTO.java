package com.example.lobbying.vacancy.dto;

import java.util.List;

public record VacancyRequestDTO(Long id, String title, String description, List<Long> tagIds, String urlForm) {
}
