package com.example.lobbying.vacancyTests;

import com.example.lobbying.vacancy.Vacancy;
import com.example.lobbying.vacancy.VacancyRepository;
import com.example.lobbying.vacancy.VacancyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VacancyServiceFindByIdTest {

    @InjectMocks
    private VacancyService vacancyService;

    @Mock
    private VacancyRepository vacancyRepository;

    public VacancyServiceFindByIdTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(1L);
        vacancy.setTags(new ArrayList<>());

        when(vacancyRepository.findById(1L)).thenReturn(Optional.of(vacancy));

        Optional<Vacancy> result = vacancyService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }
}
