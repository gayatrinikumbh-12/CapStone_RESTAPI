package data;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.javafaker.Faker;

import clients.UserClient;
import models.UserSignupRequest;
import utilities.EndpointConfig;
import utilities.TestDataFetcher;

public class TestDataBuild {

	public static UserSignupRequest payloadUserSignUP(String Email, String password) {

		String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");
		
		
	       // String email_data = TestDataManager.getRandomValidEmail();
		UserSignupRequest signupRequestModel = UserSignupRequest.builder().email(Email).password(password).build();
		return signupRequestModel;
		

	}

	public static UserSignupRequest payloadLogin(String Email, String password) {
		String email = UserClient.getInstance().createUser(Email, password).jsonPath().get("data.user.email");
		UserSignupRequest LoginRequest = UserSignupRequest.builder().email(Email).password(password).build();

		return LoginRequest;

	}

	public static UserSignupRequest cart(String Email,  String password) {

		UserSignupRequest LoginRequest = UserSignupRequest.builder().email(Email).password(password).build();

		return LoginRequest;

	}

}
