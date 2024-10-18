package com.example.lobbying.vacancyTests;

import com.example.lobbying.tag.Tag;
import com.example.lobbying.tag.TagRepository;
import com.example.lobbying.vacancy.Vacancy;
import com.example.lobbying.vacancy.VacancyRepository;
import com.example.lobbying.vacancy.VacancyService;
import com.example.lobbying.vacancy.dto.VacancyRequestDTO;
import com.example.lobbying.vacancy.dto.VacancyResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VacancyServiceCreateVacancyTest {

    @InjectMocks
    private VacancyService vacancyService;

    @Mock
    private VacancyRepository vacancyRepository;

    @Mock
    private TagRepository tagRepository;

    public VacancyServiceCreateVacancyTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVacancy() {
        VacancyRequestDTO requestDTO = new VacancyRequestDTO(1L, "Title", "Description", List.of(1L, 2L), "test");
        Vacancy vacancy = new Vacancy();
        vacancy.setId(1L);
        vacancy.setTitle("Title");
        vacancy.setDescription("Description");
        vacancy.setUrlForm("urlForm");
        vacancy.setTags(new ArrayList<>());

        when(tagRepository.findAllByIdIn(requestDTO.tagIds())).thenReturn(List.of(new Tag(), new Tag()));
        when(vacancyRepository.save(any(Vacancy.class))).thenReturn(vacancy);

        VacancyResponseDTO responseDTO = vacancyService.createVacancy(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(1L, responseDTO.id());
        assertEquals("Title", responseDTO.title());
        assertEquals("Description", responseDTO.description());
    }
}
