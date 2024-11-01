package piku_mau;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import clients.UserClient;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import utilies.AssertionsUtil;
import utilies.BaseTest;

public class LoginAPI extends BaseTest {

	@Test
	public void verifySuccessfulLoginWithValidCredentials() {
		// Response response2 = UserClient.getInstance().createUser();
		Response response = UserClient.getInstance().authenticateUser(BaseTest.getUserEmail());

		AssertionsUtil.assertResponseNotEmpty(response);
		AssertionsUtil.assertStatusCode(response, 200);
		AssertionsUtil.assertPropertyNotNull(response, "data.session.access_token", "Access tocken is null");
		AssertionsUtil.assertPropertyNotNull(response, "data.user.email", "user email is null");

		Headers headers = response.getHeaders();

		assertNotNull(headers.get("Content-Type"), "Content-Type header should not be null");
		assertEquals(headers.getValue("Content-Type"), "application/json; charset=utf-8", "Unexpected Content-Type");

	}
}