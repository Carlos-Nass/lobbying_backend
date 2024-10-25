package com.example.lobbying.vacancyTests;

import com.example.lobbying.vacancy.Vacancy;
import com.example.lobbying.vacancy.VacancyRepository;
import com.example.lobbying.vacancy.VacancyService;
import com.example.lobbying.vacancy.dto.VacancyRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VacancyServiceUpdateVacancyTest {

    @InjectMocks
    private VacancyService vacancyService;

    @Mock
    private VacancyRepository vacancyRepository;

    public VacancyServiceUpdateVacancyTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateVacancy() {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(1L);
        vacancy.setTitle("Title");
        vacancy.setDescription("Description");
        vacancy.setTags(new ArrayList<>());

        Vacancy requestDTO = new Vacancy();
        vacancy.setId(1L);
        vacancy.setTitle("Updated Title");
        vacancy.setDescription("Updated Description");
        vacancy.setTags(new ArrayList<>());

        when(vacancyRepository.findById(1L)).thenReturn(Optional.of(vacancy));
        when(vacancyRepository.save(any(Vacancy.class))).thenReturn(vacancy);

        Vacancy updatedVacancy = vacancyService.updateVacancy(1L, requestDTO);

        assertEquals("Updated Title", updatedVacancy.getTitle());
        assertEquals("Updated Description", updatedVacancy.getDescription());
    }
}
