package models;


import static org.testng.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.Assert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.Data;
import utilies.AssertionsUtil;

@Data
public class UserSignupResponse {

	private Data data;
	private int statusCode;
	private Map<String, String> headers; // Add a field for headers

	@lombok.Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Data {
		@JsonProperty("session")
		private Session session;
		@JsonProperty("user")
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
		@JsonProperty("email")
		private String email;
	}

	

	public void setHeaders(Headers headers2) {
		// TODO Auto-generated method stub
		 for (Header header : headers2) {
		        if (header.getName().equalsIgnoreCase("Content-Type")) {
		            headers.put("Content-Type", header.getValue());
		            break; // Assuming only one Content-Type header is expected
		        }
		    }
		
	}
	
	public void assertUserCreatedSuccessfully() {
		
		assertNotNull(this.getData().getUser().getId(), "User ID should not be null");
        assertNotNull(this.getData().getSession().getAccessToken(), "Access token must be present");
        int statusCode = this.getStatusCode();
        Assert.assertEquals(statusCode, 201, "Expected status code to be 201");
       
       
    }


}