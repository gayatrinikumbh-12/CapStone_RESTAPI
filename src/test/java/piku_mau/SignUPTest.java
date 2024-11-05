package piku_mau;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import clients.UserClient;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import models.UserSignupResponse;
import utilies.AssertionsUtil;
import utilies.BaseTest;

public class SignUPTest extends BaseTest {
	String email;
	

	@Test
	public void shouldCreateNewUserSuccessfully() throws IOException {
		
		userEmail.get();
		Response response = UserClient.getInstance().createUser(BaseTest.getUserEmail(), BaseTest.getuserPassword());
		UserSignupResponse userSignupResponse = response.as(UserSignupResponse.class);
				// Using the new assertion method within the POJO
		userSignupResponse.assertUserCreatedSuccessfully();

		Headers headers = response.getHeaders();
		assertNotNull(headers.get("Content-Type"), "Content-Type header should not be null");
		assertEquals(headers.getValue("Content-Type"), "application/json; charset=utf-8", "Unexpected Content-Type");
		email = userSignupResponse.getData().getUser().getEmail();
		 

	}

}
