package com.example.lobbying.vacancyTests;

import com.example.lobbying.vacancy.Vacancy;
import com.example.lobbying.vacancy.VacancyRepository;
import com.example.lobbying.vacancy.VacancyService;
import com.example.lobbying.vacancy.dto.VacancyResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VacancyServiceGetVacanciesTest {

    @InjectMocks
    private VacancyService vacancyService;

    @Mock
    private VacancyRepository vacancyRepository;

    public VacancyServiceGetVacanciesTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetVacancies() {
        Vacancy vacancy1 = new Vacancy();
        vacancy1.setTags(new ArrayList<>());
        Vacancy vacancy2 = new Vacancy();
        vacancy2.setTags(new ArrayList<>());

        List<Vacancy> vacancies = List.of(vacancy1, vacancy2);
        when(vacancyRepository.findAll()).thenReturn(vacancies);

        List<VacancyResponseDTO> result = vacancyService.getVacancies();

        assertEquals(2, result.size());
    }
}
