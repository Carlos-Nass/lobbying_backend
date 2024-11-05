package com.example.lobbying.personalityTraitTests;

import com.example.lobbying.personalityTest.*;
import com.example.lobbying.tag.Tag;
import com.example.lobbying.tag.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class CreatePersonalityTraitTest {

    @Mock
    private PersonalityTraitRepository personalityTraitRepository;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private PersonalityTraitService personalityTraitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePersonality() {
        PersonalityTraitDTO dto = new PersonalityTraitDTO();
        dto.setTrait(PersonalityTrait.OPENNESS);
        dto.setTagIds(List.of(1L, 2L));

        Tag tag1 = new Tag();
        Tag tag2 = new Tag();
        List<Tag> tags = List.of(tag1, tag2);

        when(tagRepository.findAllByIdIn(dto.getTagIds())).thenReturn(tags);

        personalityTraitService.createPersonality(dto);

        verify(personalityTraitRepository, times(1)).save(any(PersonalityTraitModel.class));
        verify(tagRepository, times(1)).findAllByIdIn(dto.getTagIds());
    }
}
