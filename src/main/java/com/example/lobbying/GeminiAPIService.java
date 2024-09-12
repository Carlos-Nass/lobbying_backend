package com.example.lobbying;

import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class GeminiAPIService {

    private static final String API_KEY = "AIzaSyAqyHGPub9JApckY5E2BvOy57WsCwYdHSU";
    private static final String REQUEST_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=%s";

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        // Formata a URL com a chave de API
        String url = String.format(REQUEST_URL, API_KEY);

        // Novo formato de payload para o pedido correto
        String jsonInputString = "{text: Ola gemini}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

        // Tenta fazer a requisição
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // Verifica a resposta
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




