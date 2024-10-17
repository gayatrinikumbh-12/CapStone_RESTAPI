package piku;

import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.EndpointConfig;
import utilities.LoginRequestModel;
import utilities.RandomNumberGenrator;
import utilities.SignupRequestModel;

public class LoginAPI {

	
	
	

	@Test
	void Verify_LoginAPI()
	{
		RandomNumberGenrator RG = new RandomNumberGenrator();
		SignUPTest SignUP = new SignUPTest();
		 String LoginEndpoint = EndpointConfig.getEndpoint("loginProfile", "Login");
		String Email = SignUP.Verify_SignUPTest();

		LoginRequestModel LoginRequest = LoginRequestModel.builder()
			    .email(Email).password("123456")
				.build();
       

        // Send POST request and capture the response
        Response LoginResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(LoginRequest)
                .post(LoginEndpoint);
        
        LoginResponse.prettyPrint();
        
}
}