package com.example.lobbying;

import com.example.lobbying.personalityTest.PersonalityTrait;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class GeminiAPIService {

    @Value("${gemini.api.key}")
    private String API_KEY;

    private static final String REQUEST_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=";

    private String getRequestUrl(){
        return REQUEST_URL + API_KEY;
    }

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
                      Com base nas respostas às perguntas e respostas fornecidas, 
                      e considerando os traços do teste de personalidade BigFive relacionados aos cinco fatores de personalidade (Openness, Conscientiousness, Extraversion, Agreeableness, Neuroticism), 
                      me devolva somente o traço correspondente ao fator que apresenta o nível mais alto de associação com a pessoa. 
                      Retorne SOMENTE o nome do traço BigFive como resultado, sem qualquer explicação, texto adicional ou traço que não faça parte dos 5 fatores a cima"
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
                    getRequestUrl(),
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





