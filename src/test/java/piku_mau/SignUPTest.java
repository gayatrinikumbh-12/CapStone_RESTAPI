package piku_mau;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import clients.UserClient;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserSignupResponse;
import utilies.Assertions;
import utilies.BaseTest;
import utilities.EndpointConfig;
import utilities.RandomNumberGenrator;

public class SignUPTest extends BaseTest {
	String email;

	@Test
	public void shouldCreateNewUserSuccessfully() throws IOException {
		userEmail.get();
		Response response = UserClient.getInstance().createUser(BaseTest.getUserEmail());
		UserSignupResponse UR = response.as(UserSignupResponse.class);
		Assertions.assertResponseNotEmpty(response);
		System.out.println("UR   " + UR.toString());
		Assertions.assertJsonPathExists(response, "data.user.id");
		Assertions.assertHeader(response, "Content-Type", "application/json; charset=utf-8");
		Assertions.assertPropertyNotNull(response, "data.user.id", "User ID is NOT NULL");
		Assertions.assertPropertyNotNull(response, "data.session.access_token", "Access token is NOT NULL");
		Assertions.assertPropertyNotNull(response, "data.user.id","ID should not be null");
		Assertions.assertStatusCode(response, 201);
		Assertions.assertHeader(response, "Content-Type", "application/json; charset=utf-8");
		
		email = UR.getData().getUser().getEmail();
	}

}
