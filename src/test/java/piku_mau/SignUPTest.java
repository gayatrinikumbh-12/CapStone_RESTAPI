package piku_mau;


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
import utilies.BaseTest;
import utilities.EndpointConfig;
import utilities.RandomNumberGenrator;



public class SignUPTest extends BaseTest{
	 String email;
	@Step("Creating a new user")
	@Test(groups={"Login"})

	public void shouldCreateNewUserSuccessfully() throws IOException
	{
		userEmail.get();
		Response response = UserClient.getInstance().createUser(BaseTest.getUserEmail());
		UserSignupResponse UR = response.as(UserSignupResponse.class);
		System.out.println("UR   "+UR.toString()); 
		 assertNotNull(UR.getData().getUser().getId(), "User ID should not be null");
		assertNotNull(UR.getData().getSession().getAccessToken(), "Access token must be present");
		// assertThat(UR., equalTo(200));
	     assertValueNotNull(response, "data.userID");
	    assertSuccessStatusCode(response,201 , "expected 201 response ");
	    assertHeaderValue(response, "Content-Type", "application/json; charset=utf-8");
	    // assertThat("Expected content-type header to be present",
	             //  UR.getHeaders().get("Content-Type"), equalTo("application/json"));
	
	   // String email = response.jsonPath().get("data.user.email");
	   email = UR.getData().getUser().getEmail();
	}
	
	
}
