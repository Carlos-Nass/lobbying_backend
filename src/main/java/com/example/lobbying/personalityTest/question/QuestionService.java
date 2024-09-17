package com.example.lobbying.personalityTest.question;

import com.example.lobbying.GeminiAPIService;
import com.example.lobbying.personalityTest.PersonalityTrait;
import com.example.lobbying.personalityTest.answer.AnswerDTO;
import com.example.lobbying.user.User;
import com.example.lobbying.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }


}

