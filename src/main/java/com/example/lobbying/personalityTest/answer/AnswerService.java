package com.example.lobbying.personalityTest.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public void saveAnswers(List<Answer> answers) {
        this.answerRepository.saveAll(answers);



    }
}

