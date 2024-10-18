package com.example.lobbying;

import com.example.lobbying.personalityTest.PersonalityTrait;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class GeminiAPIService {

    private static final String REQUEST_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=AIzaSyDqt0lSWc1jFSsey8yPVg-RBfZIUQh2LGo";

    Logger logger = Logger.getLogger(getClass().getName());

    public PersonalityTrait doCallGemini(String question) {
        RestTemplate restTemplate = new RestTemplate();

        String jsonInputString = """
            {
              "contents": [
                {
                  "parts": [
                    {
                      "text": "Baseado nessas perguntas e respostas: %s. 
                      Levando em conta o teste de personalidade DISC, me devolva SOMENTE essas respectivas ENUMs que 
                      correspondem a personalidade desse individuo OPENNESS, CONSCIENTIOUSNESS, EXTRAVERSION, AGREEABLENESS, NEUROTICISM.
                      preciso que me devolva somente a ENUM, sem texto explicando, e quero também que me devolva a ENUM que tem o nivel alto de relação com a pessoa,
                      quero apenas um resultado, o que for mais associado a pessoa, quero que a resposta seja literalmente e somente a ENUM, quero somente o maior resultado."
                    }
                  ]
                }
              ]
            }""".formatted(question);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

        try {

            ResponseEntity<String> response = restTemplate.exchange(
            		REQUEST_URL,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                String responseBody = response.getBody();

                JSONObject jsonResponse = new JSONObject(responseBody);

                JSONArray candidates = jsonResponse.getJSONArray("candidates");
                JSONObject candidate = candidates.getJSONObject(0);
                JSONObject content = candidate.getJSONObject("content");
                JSONArray parts = content.getJSONArray("parts");
                JSONObject part = parts.getJSONObject(0);

                String text = part.getString("text");
                return extractTrait(text);
            } else {
                throw new RuntimeException("Ocorreu um erro ao gerar teste de personalidade");
            }
        } catch (HttpClientErrorException e) {
            logger.info("Erro na requisição: " + e.getMessage());
            logger.info("Resposta do servidor: " + e.getResponseBodyAsString());

            throw new RuntimeException("Ocorreu um erro ao gerar teste de personalidade");
        }
    }

    public static PersonalityTrait extractTrait(String response) {
        String normalizedResponse = response.toUpperCase();

        if (normalizedResponse.contains("OPENNESS")) {
            return PersonalityTrait.OPENNESS;
        }

        if (normalizedResponse.contains("CONSCIENTIOUSNESS")) {
            return PersonalityTrait.CONSCIENTIOUSNESS;
        }

        if (normalizedResponse.contains("EXTRAVERSION")) {
            return PersonalityTrait.EXTRAVERSION;
        }

        if (normalizedResponse.contains("AGREEABLENESS")) {
            return PersonalityTrait.AGREEABLENESS;
        }

        if (normalizedResponse.contains("NEUROTICISM")) {
            return PersonalityTrait.NEUROTICISM;
        }

        return null;
    }
}





