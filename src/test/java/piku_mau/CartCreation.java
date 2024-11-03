package piku_mau;

import java.io.IOException;

import org.testng.annotations.Test;

import clients.UserClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CartRequest;
import models.UserSignupResponse;
import utilies.AssertionsUtil;
import utilies.BaseTest;


public class CartCreation extends BaseTest {

	@Test
	void create_new_cart_for_user() throws IOException {
		// Response response1 = UserClient.getInstance().createUser();
		Response response = UserClient.getInstance().CreateCart(BaseTest.getUserEmail(), BaseTest.getuserPassword());
		response.prettyPrint();
		
		CartRequest CartResponse = response.as(CartRequest.class);

		// Using the new assertion method within the POJO
		CartResponse.assertCartCreatedSuccessfully();

		
		AssertionsUtil.assertStatusCode(response, 201);
		AssertionsUtil.assertStatusLine(response, "HTTP/1.1 201 Created");
		

	}

	@Test
	void cart_already_exists_for_user() throws IOException {
		String Email = BaseTest.getUserEmail();
		String Password = BaseTest.getuserPassword();
		Response response = UserClient.getInstance().CreateCart(Email,Password);
		response.prettyPrint();
		AssertionsUtil.assertResponseNotEmpty(response);
		CartRequest UR = response.as(CartRequest.class);
		String CreatedUser = UR.getUser_id();
		System.out.println("piku " + CreatedUser);

		// now use same email to create cart

		// Response response1 = UserClient.getInstance().createUser();
		Response responseForSameEmail = UserClient.getInstance().CreateCart(Email,Password);
		responseForSameEmail.prettyPrint();

		String expectedMsg = responseForSameEmail.jsonPath().get("error");
		
	}

}
