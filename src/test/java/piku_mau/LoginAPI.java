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
		
		
		
	}
}