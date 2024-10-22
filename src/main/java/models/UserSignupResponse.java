package models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.restassured.response.Response;
import lombok.Data;





@Data
public class UserSignupResponse {
    
	 private Data data;
	 private int statusCode;
	 private Map<String, String>  headers; // Add a field for headers
	 
	 @lombok.Data
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 public static class Data {
	        private Session session;
	        private User user;
	    }

	 @lombok.Data
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 public static class Session {
	        @JsonProperty("access_token")
	        private String accessToken;
	    }
	 
	 @lombok.Data
	 @JsonIgnoreProperties(ignoreUnknown = true)
	 public static class User {
	        @JsonProperty("id")
	        private String id;
	    }
	 
	 @lombok.Data
	 public class ResponseHeaders {
		 @JsonProperty("statusCode")
		 private int statusCode;
		
		 @JsonProperty("contentType")
	     private String contentType;
	     
	 }
	 
	
   
}