package com.example.lobbying.vacancyTests;

import com.example.lobbying.tag.Tag;
import com.example.lobbying.user.User;
import com.example.lobbying.user.UserRepository;
import com.example.lobbying.vacancy.Vacancy;
import com.example.lobbying.vacancy.VacancyRepository;
import com.example.lobbying.vacancy.VacancyService;
import com.example.lobbying.vacancy.dto.VacancyDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VacancyServiceLoadVacanciesByTagsTest {

    @InjectMocks
    private VacancyService vacancyService;

    @Mock
    private VacancyRepository vacancyRepository;

    @Mock
    private UserRepository userRepository;

    public VacancyServiceLoadVacanciesByTagsTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadVacanciesByTags() {
        User user = new User();
        user.setId(1L);

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Tag1");

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName("Tag2");

        user.setTags(List.of(tag1, tag2));

        Vacancy vacancy1 = new Vacancy();
        vacancy1.setTags(new ArrayList<>());

        Vacancy vacancy2 = new Vacancy();
        vacancy2.setTags(new ArrayList<>());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(vacancyRepository.findAllByTagsIdIn(List.of(1L, 2L))).thenReturn(List.of(vacancy1, vacancy2));

        List<VacancyDTO> result = vacancyService.loadVacanciesByTags(1L);

        assertEquals(2, result.size());
    }

    @Test
    void testLoadVacanciesByTags_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            vacancyService.loadVacanciesByTags(1L);
        });

        assertEquals("Usuário com o id:1. Não encontrado.", exception.getMessage());
    }
}
