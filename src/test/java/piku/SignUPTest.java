package piku;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.EndpointConfig;
import utilities.RandomNumberGenrator;
import utilities.SignupRequestModel;

public class SignUPTest extends BaseTest{

	@Test
	String Verify_SignUPTest()
	{
		RandomNumberGenrator RG = new RandomNumberGenrator();
		 String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");
		String randomEmail = RG.RandomEmailGenrator();

		SignupRequestModel signupRequestModel = SignupRequestModel.builder().email(randomEmail).password("123456")
				.build();
       

        // Send POST request and capture the response
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(signupRequestModel)
                .post(signUpEndpoint);
        
        response.prettyPrint();
        
        assertThat(response.getStatusCode(), Matchers.equalTo(201));
    	assertThat(response.getStatusLine(), Matchers.contains("Created") != null);
	
    	return randomEmail;
	}
	
	
}
