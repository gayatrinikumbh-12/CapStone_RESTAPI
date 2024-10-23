package piku;


import static org.testng.Assert.assertNotNull;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import clients.UserClient;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserSignupResponse;
import utilities.EndpointConfig;
import utilities.RandomNumberGenrator;



public class SignUPTest extends BaseTest{
	
	@Step("Creating a new user")
	@Test(groups={"parallel"})
	void shouldCreateNewUserSuccessfully() throws IOException
	{
		
		Response response = UserClient.getInstance().createUser();
		UserSignupResponse UR = response.as(UserSignupResponse.class);
		System.out.println("UR   "+UR.toString()); 
		 assertNotNull(UR.getData().getUser().getId(), "User ID should not be null");
		assertNotNull(UR.getData().getSession().getAccessToken(), "Access token must be present");
		// assertThat(UR., equalTo(200));
	     ApiResUtilities.assertValueNotNull(response, "data.userID");
	    ApiResUtilities.assertSuccessStatusCode(response,201 , "expected 201 response ");
	    ApiResUtilities.assertHeaderValue(response, "Content-Type", "application/json; charset=utf-8");
	    // assertThat("Expected content-type header to be present",
	             //  UR.getHeaders().get("Content-Type"), equalTo("application/json"));
	
	}
	
	
}
