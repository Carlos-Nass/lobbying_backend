package com.example.lobbying.questionTest;

import com.example.lobbying.personalityTest.answer.Answer;
import com.example.lobbying.personalityTest.answer.AnswerRepository;
import com.example.lobbying.personalityTest.answer.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class SaveAnswerTest {

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private AnswerService answerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAnswers() {
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        List<Answer> answers = List.of(answer1, answer2);

        answerService.saveAnswers(answers);

        verify(answerRepository, times(1)).saveAll(answers);
    }
}
