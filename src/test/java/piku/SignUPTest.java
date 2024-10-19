package piku;


import static org.testng.Assert.assertNotNull;
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
	void shouldCreateNewUserSuccessfully()
	{
		Response response = UserClient.getInstance().createUser();
		UserSignupResponse UR = response.as(UserSignupResponse.class);
		System.out.println("UR   "+UR); 
		 assertNotNull(UR.getData().getID(), "User ID should not be null");
		 assertNotNull(UR.getData().getSession().getAccessToken(), "Access token must be present");
		 //assertThat(UR.getStatusCode(), equalTo(200), "Status code must be 200");
	   // ApiResUtilities.assertValueNotNull(response, "data.userID");
	   // ApiResUtilities.assertSuccessStatusCode(response,201 , "expected 201 response ");
	}
	
	
}
