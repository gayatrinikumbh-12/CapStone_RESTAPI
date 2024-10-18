package piku;


import static org.testng.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import clients.UserClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.EndpointConfig;
import utilities.RandomNumberGenrator;
import utilities.SignupRequestModel;

public class SignUPTest extends BaseTest{
	
	@Test
	void shouldCreateNewUserSuccessfully()
	{
		Response response = UserClient.getInstance().Verify_SignUPTest();
		int statusCode = response.getStatusCode();
	    assertThat("Unexpected status code", statusCode, equalTo(201));

	    String userId = response.jsonPath().getString("data.userId");
	    assertNotNull("User ID should be present in the response", userId);
	}
	
	
}
