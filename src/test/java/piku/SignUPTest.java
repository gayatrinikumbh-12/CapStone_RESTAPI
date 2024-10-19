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
		Response response = UserClient.getInstance().createUser();
		
	    ApiResUtilities.assertCreatedStatusCode(response);
	   
	    ApiResUtilities.assertValueNotNull(response, "data.userID");
	}
	
	
}
