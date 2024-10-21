package models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.restassured.response.Response;
import lombok.Data;


@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSignupResponse {
    private int statusCode;
    private Data data;
    private Map<String, String> headers; // Add a field for headers

    
    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private Session session;
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Session {
        @JsonProperty("access_token")
        private String accessToken;
    }
}