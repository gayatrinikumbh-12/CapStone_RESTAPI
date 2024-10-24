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


public class LoginAPI extends BaseTest{

	
	
	
	@Step("Login with correct cred")
	@Test(description = "Verify successful login with valid credentials",groups={"parallel"})
	public void verifySuccessfulLoginWithValidCredentials()
	{
		//Response response2 = UserClient.getInstance().createUser();
		 Response response=UserClient.getInstance().authenticateUser(BaseTest.getUserEmail());
		 
		    
		    ApiResUtilities.assertSuccessStatusCode(response,200 , "expected 200 response ");
		    ApiResUtilities.assertValueNotNull(response,"data.session.access_token");
		    ApiResUtilities.assertValueNotNull(response,"data.user.email");
		    ApiResUtilities.assertResponseContains(response, "access_token", "The response does not contain the expected value.");
		  
		    //assertThat("User token should be present and have the expected format", userToken, matchesPattern("^Bearer [\\w-\\.]+(.[\\w-]+)*$")); // Example regex pattern for a token
	}
}