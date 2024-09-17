package com.example.lobbying.personalityTest.answer;

import com.example.lobbying.personalityTest.question.QuestionDTO;
import lombok.Data;

import java.util.List;

@Data
public class AnswerDTO {

    private Long userId;

    private List<QuestionDTO> questions;

}
