package piku;

import static org.hamcrest.MatcherAssert.assertThat;

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
	void Verify_SignUPTest()
	{
		UserClient.getInstance().Verify_SignUPTest();
		
	}
	
	
}
