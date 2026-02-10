package br.com.ScreenMusic.service;


import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;



public class DescreveArtista {

    public static String obterInformacao(String texto) {
        String apiKey = System.getenv("GEMINI_API_KEY");;

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("API Key do Gemini não encontrada");
        }

        Client client = Client.builder()
                .apiKey(apiKey)
                .build();

        try {
            GenerateContentResponse response = client.models.generateContent(
                    "gemini-2.0-flash",
                    "Me fale sobre o artista " + texto,
                    null
            );

            return response.text();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao chamar a API do Gemini", e);
        }
    }

}

