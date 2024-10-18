package com.example.lobbying.personalityTraitTests;

import com.example.lobbying.GeminiAPIService;
import com.example.lobbying.personalityTest.PersonalityTrait;
import com.example.lobbying.personalityTest.PersonalityTraitModel;
import com.example.lobbying.personalityTest.PersonalityTraitRepository;
import com.example.lobbying.personalityTest.PersonalityTraitService;
import com.example.lobbying.personalityTest.answer.AnswerDTO;
import com.example.lobbying.personalityTest.question.QuestionDTO;
import com.example.lobbying.user.User;
import com.example.lobbying.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GeneratePersonalityTraitTest {

    @Mock
    private PersonalityTraitRepository personalityTraitRepository;

    @Mock
    private GeminiAPIService geminiAPIService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PersonalityTraitService personalityTraitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGeneratePersonality() {
        AnswerDTO dto = new AnswerDTO();
        dto.setUserId(1L);
        dto.setQuestions(List.of(new QuestionDTO()));

        PersonalityTrait personalityTrait = PersonalityTrait.OPENNESS;
        when(geminiAPIService.doCallGemini(anyString())).thenReturn(personalityTrait);

        User user = new User();
        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(user));

        PersonalityTraitModel traitModel = new PersonalityTraitModel();
        traitModel.setPersonalityTrait(personalityTrait);
        traitModel.setTags(new ArrayList<>());
        when(personalityTraitRepository.findByPersonalityTrait(personalityTrait)).thenReturn(Optional.of(traitModel));

        personalityTraitService.generatePersonality(dto);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGeneratePersonality_UserNotFound() {
        AnswerDTO dto = new AnswerDTO();
        dto.setUserId(1L);
        dto.setQuestions(List.of(new QuestionDTO()));

        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personalityTraitService.generatePersonality(dto));
    }

    @Test
    void testGeneratePersonality_TraitNotFound() {
        AnswerDTO dto = new AnswerDTO();
        dto.setUserId(1L);
        dto.setQuestions(List.of(new QuestionDTO()));

        PersonalityTrait personalityTrait = PersonalityTrait.OPENNESS;
        when(geminiAPIService.doCallGemini(anyString())).thenReturn(personalityTrait);

        User user = new User();
        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(user));

        when(personalityTraitRepository.findByPersonalityTrait(personalityTrait)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personalityTraitService.generatePersonality(dto));
    }
}
