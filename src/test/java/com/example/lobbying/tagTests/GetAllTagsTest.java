package com.example.lobbying.tagTests;

import com.example.lobbying.controller.TagController;
import com.example.lobbying.tag.Tag;
import com.example.lobbying.tag.TagRepository;
import com.example.lobbying.tag.TagResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TagControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagController tagController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
    }

    @Test
    void testGetAll() throws Exception {
        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Tag1");

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName("Tag2");

        List<TagResponseDTO> tagList = List.of(new TagResponseDTO(tag1), new TagResponseDTO(tag2));
        when(tagRepository.findAll()).thenReturn(List.of(tag1, tag2));

        mockMvc.perform(get("/tag")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(tagList.size()));

        verify(tagRepository, times(1)).findAll();
    }
}
