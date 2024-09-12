package com.example.lobbying;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class GeminiAPIService {

    private static final String API_KEY = "AIzaSyCuHSvSo3u6KrJiMKpUZTxSeQcBFAe6FC8";
    private static final String REQUEST_URL = "https://generativelanguage.googleapis.com/v1beta2/models/gemini-1.5-flash-001:generateMessage";

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        String url = String.format(REQUEST_URL, API_KEY);

        String jsonInputString = "{ \"prompt\": { \"messages\": [{ \"content\": \"Olá, Gemini!\" }] } }";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
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





