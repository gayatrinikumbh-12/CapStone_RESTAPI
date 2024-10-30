package piku_mau;

import static org.hamcrest.Matchers.matchesPattern;
import static org.testng.Assert.assertNotNull;

import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import clients.UserClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utilies.Assertions;
import utilies.BaseTest;

public class LoginAPI extends BaseTest {

	@Test
	public void verifySuccessfulLoginWithValidCredentials() {
		// Response response2 = UserClient.getInstance().createUser();
		Response response = UserClient.getInstance().authenticateUser(BaseTest.getUserEmail());

		Assertions.assertResponseNotEmpty(response);
		assertSuccessStatusCode(response, 200, "expected 200 response ");
		Assertions.assertStatusCode(response, 200);
		assertValueNotNull(response, "data.session.access_token");
		Assertions.assertPropertyNotNull(response, "data.session.access_token", "Access tocken is null");
		assertValueNotNull(response, "data.user.email");
		Assertions.assertPropertyNotNull(response, "data.user.email", "user email is null");
		assertResponseContains(response, "access_token", "The response does not contain the expected value.");
		
		// assertThat("User token should be present and have the expected format",
		// userToken, matchesPattern("^Bearer [\\w-\\.]+(.[\\w-]+)*$")); // Example
		// regex pattern for a token
	}
}