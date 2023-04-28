package com.kolefni.tracker.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kolefni.tracker.service.api.ApiService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiServiceImpl implements ApiService {

    private String root = "https://carbonsutra1.p.rapidapi.com/";
    private final ObjectMapper objectMapper;

    public ApiServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String makeApiCall(String apiUrl, String queryParams) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(root + apiUrl))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("Authorization", "Bearer fQ98oU704xFvsnXcQLVDbpeCJHPglG1DcxiMLKfpeNEMGumlbzVf1lCI6ZBx")
                .header("X-RapidAPI-Key", "9b528abe82msh2afc628cff721fcp113426jsnd41e248643b3")
                .header("X-RapidAPI-Host", "carbonsutra1.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString(queryParams))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }
}



