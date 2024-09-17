package com.example.lobbying.personalityTest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityTraitRepository extends JpaRepository<PersonalityTraitModel, Long> {

    PersonalityTraitModel findByPersonalityTrait(PersonalityTrait trait);

}
