package piku_mau;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import clients.UserClient;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import models.UserSignupResponse;
import utilies.AssertionsUtil;
import utilies.BaseTest;

public class LoginAPI extends BaseTest {
	private static final Logger logger = LogManager.getLogger(LoginAPI.class);
	@Test
	public void verifySuccessfulLoginWithValidCredentials() {
		// Response response2 = UserClient.getInstance().createUser();
		 logger.info("Starting 'verifySuccessfulLoginWithValidCredentials' test case");
		Response response = UserClient.getInstance().authenticateUser(BaseTest.getUserEmail(), BaseTest.getuserPassword());
		logger.info("Response received with status code: {}", response.getStatusCode());
		System.out.println("login yes "+response.prettyPrint());
		UserSignupResponse userSignupResponse = response.as(UserSignupResponse.class);

		// Using the new assertion method within the POJO
		userSignupResponse.assertUserCreatedSuccessfully();
		AssertionsUtil.assertResponseNotEmpty(response);
		AssertionsUtil.assertStatusCode(response, 200);
		AssertionsUtil.assertPropertyNotNull(response, "data.session.access_token", "Access tocken is null");
		AssertionsUtil.assertPropertyNotNull(response, "data.user.email", "user email is null");

		Headers headers = response.getHeaders();

		assertNotNull(headers.get("Content-Type"), "Content-Type header should not be null");
		assertEquals(headers.getValue("Content-Type"), "application/json; charset=utf-8", "Unexpected Content-Type");
		logger.info("'verifySuccessfulLoginWithValidCredentials' has been successfully created.", userEmail);
	}
}