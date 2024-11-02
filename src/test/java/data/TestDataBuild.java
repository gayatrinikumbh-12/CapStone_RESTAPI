package data;

import com.fasterxml.jackson.databind.JsonNode;

import clients.UserClient;
import models.UserSignupRequest;
import utilities.EndpointConfig;
import utilities.TestDataFetcher;

public class TestDataBuild {

	public static UserSignupRequest payloadUserSignUP(String Email) {

		//String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");
		 JsonNode validUsers = TestDataFetcher.getTestDataNode("validUsers");
	        String email_data = validUsers.get(0).get("email").asText();
	        String password_data = validUsers.get(0).get("password").asText();
		
		UserSignupRequest signupRequestModel = UserSignupRequest.builder().email(email_data).password(password_data).build();
		return signupRequestModel;

	}

	public static UserSignupRequest payloadLogin(String Email) {
		String email = UserClient.getInstance().createUser(Email).jsonPath().get("data.user.email");
		UserSignupRequest LoginRequest = UserSignupRequest.builder().email(email).password("123456").build();

		return LoginRequest;

	}

	public static UserSignupRequest cart(String Email) {

		UserSignupRequest LoginRequest = UserSignupRequest.builder().email(Email).password("123456").build();

		return LoginRequest;

	}

}
