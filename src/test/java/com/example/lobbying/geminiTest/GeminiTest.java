package com.example.lobbying.geminiTest;

import com.example.lobbying.GeminiAPIService;
import com.example.lobbying.personalityTest.PersonalityTrait;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class GeminiAPIServiceTest {

    @InjectMocks
    private GeminiAPIService geminiAPIService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoCallGemini_HttpClientErrorException() {
        String question = "Sample question";

        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Bad Request"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> geminiAPIService.doCallGemini(question));

        assertEquals("Ocorreu um erro ao gerar teste de personalidade", exception.getMessage());
    }

    @Test
    void testExtractTrait() {
        assertEquals(PersonalityTrait.OPENNESS, GeminiAPIService.extractTrait("OPENNESS"));
        assertEquals(PersonalityTrait.CONSCIENTIOUSNESS, GeminiAPIService.extractTrait("CONSCIENTIOUSNESS"));
        assertEquals(PersonalityTrait.EXTRAVERSION, GeminiAPIService.extractTrait("EXTRAVERSION"));
        assertEquals(PersonalityTrait.AGREEABLENESS, GeminiAPIService.extractTrait("AGREEABLENESS"));
        assertEquals(PersonalityTrait.NEUROTICISM, GeminiAPIService.extractTrait("NEUROTICISM"));
        assertNull(GeminiAPIService.extractTrait("UNKNOWN"));
    }
}

