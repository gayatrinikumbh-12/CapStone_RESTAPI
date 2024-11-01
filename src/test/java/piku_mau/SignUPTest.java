package piku_mau;

import static org.testng.Assert.assertEquals;
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
import io.restassured.http.Headers;
import io.restassured.response.Response;
import models.UserSignupResponse;
import utilies.AssertionsUtil;
import utilies.BaseTest;
import utilities.EndpointConfig;
import utilities.RandomNumberGenrator;

public class SignUPTest extends BaseTest {
	String email;

	@Test
	public void shouldCreateNewUserSuccessfully() throws IOException {
		userEmail.get();
		Response response = UserClient.getInstance().createUser(BaseTest.getUserEmail());
		UserSignupResponse UR = response.as(UserSignupResponse.class);
		AssertionsUtil.assertResponseNotEmpty(response);
		System.out.println("UR   " + UR.toString());
		UserSignupResponse userSignupResponse = response.as(UserSignupResponse.class);

        // Using the new assertion method within the POJO
        userSignupResponse.assertUserCreatedSuccessfully();
		AssertionsUtil.assertResponseNotEmpty(response);
	       AssertionsUtil.assertStatusCode(response, 201);
	       AssertionsUtil.assertHeader(response, "Content-Type", "application/json; charset=utf-8");
		AssertionsUtil.assertStatusCode(response, 201);
		AssertionsUtil.assertJsonPathExists(response, "data.user.id");
		AssertionsUtil.assertHeader(response, "Content-Type", "application/json; charset=utf-8");
		AssertionsUtil.assertPropertyNotNull(response, "data.user.id", "User ID is NOT NULL");
		AssertionsUtil.assertPropertyNotNull(response, "data.session.access_token", "Access token is NOT NULL");
	    AssertionsUtil.assertHeader(response, "Content-Type", "application/json; charset=utf-8");
	    Headers headers = response.getHeaders();

	    assertNotNull(headers.get("Content-Type"), "Content-Type header should not be null");
	    assertEquals(headers.getValue("Content-Type"), "application/json; charset=utf-8", "Unexpected Content-Type");
		email = UR.getData().getUser().getEmail();
	}

}
