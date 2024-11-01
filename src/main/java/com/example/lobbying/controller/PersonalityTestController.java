package com.example.lobbying.controller;

import com.example.lobbying.personalityTest.PersonalityTraitDTO;
import com.example.lobbying.personalityTest.PersonalityTraitService;
import com.example.lobbying.personalityTest.answer.Answer;
import com.example.lobbying.personalityTest.answer.AnswerDTO;
import com.example.lobbying.personalityTest.answer.AnswerService;
import com.example.lobbying.personalityTest.question.Question;
import com.example.lobbying.personalityTest.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personality-test")
public class PersonalityTestController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private PersonalityTraitService personalityTraitService;

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/submit-answers")
    public ResponseEntity<?> submitAnswers(@RequestBody List<Answer> answers) {
        answerService.saveAnswers(answers);
        return ResponseEntity.ok("Answers submitted successfully.");
    }

    @PostMapping("/generate-personality")
    public void generatePersonality(@RequestBody AnswerDTO dto) {
        this.personalityTraitService.generatePersonality(dto);
    }

    @PostMapping("/create-personality-trait")
    public void createPersonality(@RequestBody PersonalityTraitDTO dto) {
        this.personalityTraitService.createPersonality(dto);
    }
}

