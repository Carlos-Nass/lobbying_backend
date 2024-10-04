package com.example.lobbying.personalityTest;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalityTraitRepository extends JpaRepository<PersonalityTraitModel, Long> {

    Optional<PersonalityTraitModel> findByPersonalityTrait(PersonalityTrait trait);

}
