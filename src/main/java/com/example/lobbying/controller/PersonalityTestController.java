package com.example.lobbying.controller;

import com.example.lobbying.personalityTest.answer.Answer;
import com.example.lobbying.personalityTest.answer.AnswerService;
import com.example.lobbying.personalityTest.question.Question;
import com.example.lobbying.personalityTest.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personality-test")
public class PersonalityTestController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/submit-answers")
    public ResponseEntity<?> submitAnswers(@RequestBody List<Answer> answers) {
        answerService.saveAnswers(answers);
        return ResponseEntity.ok("Answers submitted successfully.");
    }
}

