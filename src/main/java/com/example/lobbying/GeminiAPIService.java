package com.example.lobbying;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class GeminiAPIService {

    @Value("${api.key}")
    private static String API_KEY;

    private static final String REQUEST_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=AIzaSyC-utzVplVNldc2crvuVA1KVrDaqeOpY2M";

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        String jsonInputString = """
                {
                  "contents": [
                    {
                      "parts": [
                        {
                          "text": "Explique como o Gemini funciona"
                        }
                      ]
                    }
                  ]
                }""";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

        try {

            String requestUrl = String.format(REQUEST_URL, API_KEY);

            ResponseEntity<String> response = restTemplate.exchange(
                    requestUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Resposta da API: " + response.getBody());
            } else {
                System.out.println("Erro: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            System.out.println("Erro na requisição: " + e.getMessage());
            System.out.println("Resposta do servidor: " + e.getResponseBodyAsString());
        }
    }
}





