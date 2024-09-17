package com.example.lobbying.personalityTest;

import lombok.Data;

import java.util.List;

@Data
public class PersonalityTraitDTO {

    private PersonalityTrait trait;

    private List<Long> tagIds;

}
