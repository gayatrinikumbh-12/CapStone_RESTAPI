package piku;

import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

import clients.UserClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.EndpointConfig;
import utilities.LoginRequestModel;
import utilities.RandomNumberGenrator;


public class LoginAPI {

	
	
	

	@Test
	void Verify_LoginAPI()
	{
		UserClient.getInstance().authenticateUser();
		
}
}