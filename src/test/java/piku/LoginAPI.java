package piku;



import static org.testng.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import clients.UserClient;

import io.restassured.response.Response;


public class LoginAPI {

	
	
	

	@Test
	void verifySuccessfulLoginWithValidCredentials()
	{
		 Response response =UserClient.getInstance().authenticateUser();
		 int statusCode = response.getStatusCode();
		    assertThat("Expected status code 200 for successful login", statusCode, equalTo(200));

		    // Include assertions to verify the presence of a user-specific token or identifier in the response
		    String userToken = response.jsonPath().getString("token");
		    assertNotNull("User token should be present in the response", userToken);
}
}