package com.example.lobbying.answerTests;

import com.example.lobbying.personalityTest.question.Question;
import com.example.lobbying.personalityTest.question.QuestionRepository;
import com.example.lobbying.personalityTest.question.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetAllAnswerTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllQuestions() {
        Question question1 = new Question();
        Question question2 = new Question();
        List<Question> questions = List.of(question1, question2);

        when(questionRepository.findAll()).thenReturn(questions);

        List<Question> result = questionService.getAllQuestions();

        assertEquals(2, result.size());
        verify(questionRepository, times(1)).findAll();
    }
}
