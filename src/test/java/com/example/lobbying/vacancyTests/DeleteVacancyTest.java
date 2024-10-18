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

import static org.mockito.Mockito.*;

class VacancyServiceDeleteVacancyTest {

    @InjectMocks
    private VacancyService vacancyService;

    @Mock
    private VacancyRepository vacancyRepository;

    public VacancyServiceDeleteVacancyTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteVacancy() {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(1L);
        vacancy.setTags(new ArrayList<>());

        when(vacancyRepository.findById(1L)).thenReturn(Optional.of(vacancy));
        doNothing().when(vacancyRepository).delete(vacancy);

        vacancyService.deleteVacancy(1L);

        verify(vacancyRepository, times(1)).delete(vacancy);
    }
}
