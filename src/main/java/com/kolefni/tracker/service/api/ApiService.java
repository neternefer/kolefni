package com.kolefni.tracker.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.catalina.connector.Response;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

public interface ApiService {

    String makeApiCall(String apiUrl,String bodyValues) throws IOException, InterruptedException;

}

