package piku;


import static org.hamcrest.Matchers.matchesPattern;
import static org.testng.Assert.assertNotNull;

import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import clients.UserClient;

import io.restassured.response.Response;


public class LoginAPI extends BaseTest{

	
	
	

	@Test
	void verifySuccessfulLoginWithValidCredentials()
	{
		 Response response =UserClient.getInstance().authenticateUser();
		 //int statusCode = response.getStatusCode();
		    //assertThat("Expected status code 200 for successful login", statusCode, equalTo(200));
		    ApiResUtilities.assertSuccessStatusCode(response);
		    // Include assertions to verify the presence of a user-specific token or identifier in the response
		   // String userToken = response.jsonPath().getString("data.session.access_token");
		   // String email = response.jsonPath().get("data.user.email");
		   // assertThat(email,Matchers.notNullValue());
		    ApiResUtilities.assertValueNotNull(response,"data.session.access_token");
		    ApiResUtilities.assertValueNotNull(response,"data.user.email");
		    ApiResUtilities.assertResponseContains(response, "access_token", "The response does not contain the expected value.");
		    //   assertNotNull("User token should be present in the response", userToken);
		 // System.out.println("UT  "+userToken);
		    //assertThat("User token should be present and have the expected format", userToken, matchesPattern("^Bearer [\\w-\\.]+(.[\\w-]+)*$")); // Example regex pattern for a token
	}
}