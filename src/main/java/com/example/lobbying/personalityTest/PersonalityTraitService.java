package com.example.lobbying.personalityTest;

import com.example.lobbying.GeminiAPIService;
import com.example.lobbying.personalityTest.answer.AnswerDTO;
import com.example.lobbying.tag.Tag;
import com.example.lobbying.tag.TagRepository;
import com.example.lobbying.user.User;
import com.example.lobbying.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonalityTraitService {

    private PersonalityTraitRepository personalityTraitRepository;

    private TagRepository tagRepository;

    private GeminiAPIService geminiAPIService;

    private UserRepository userRepository;

    public void createPersonality(PersonalityTraitDTO dto) {
        PersonalityTraitModel personalityTraitModel = new PersonalityTraitModel();
        personalityTraitModel.setPersonalityTrait(dto.getTrait());

        List<Tag> tags = this.tagRepository.findAllByIdIn(dto.getTagIds());
        tags.forEach(tag -> tag.setPersonalityTrait(personalityTraitModel));

        personalityTraitModel.setTags(tags);

        this.personalityTraitRepository.save(personalityTraitModel);
    }

    public void generatePersonality(AnswerDTO dto) {
        StringBuilder promptQuestions = new StringBuilder();

        dto.getQuestions().forEach(question -> {
            promptQuestions.append("Pergunta: ").append(question.getQuestion());
            promptQuestions.append("Resposta: ").append(question.getAnswer());
            promptQuestions.append("|");
        });

        PersonalityTrait personalityTrait = this.geminiAPIService.doCallGemini(promptQuestions.toString());
        if (personalityTrait == null) {
            throw new RuntimeException("Ocorreu um erro ao gerar teste de personalidade");
        }

        Optional<User> optionalUser = this.userRepository.findById(dto.getUserId());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Ocorreu um erro ao buscar usuário");
        }

        PersonalityTraitModel trait = this.personalityTraitRepository.findByPersonalityTrait(personalityTrait);

        User user = optionalUser.get();
        user.setPersonalityTrait(trait.getPersonalityTrait());
        user.setTags(new ArrayList<>(trait.getTags()));

        this.userRepository.save(user);
    }
}
