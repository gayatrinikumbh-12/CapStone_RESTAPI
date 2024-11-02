package data;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.javafaker.Faker;

import clients.UserClient;
import models.UserSignupRequest;
import utilities.EndpointConfig;
import utilities.TestDataFetcher;

public class TestDataBuild {

	public static UserSignupRequest payloadUserSignUP(String Email) {

		String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");
		
		Faker faker = new Faker();
	    String email_f = faker.internet().emailAddress();
	    String password_f = faker.internet().password(8, 16);
		
	       // String email_data = TestDataManager.getRandomValidEmail();
		UserSignupRequest signupRequestModel = UserSignupRequest.builder().email(email_f).password(password_f).build();
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
