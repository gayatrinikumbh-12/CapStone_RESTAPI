package piku_mau;

import java.io.IOException;

import org.testng.annotations.Test;

import clients.UserClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CartRequest;
import models.UserSignupResponse;
import utilies.Assertions;
import utilies.BaseTest;




public class CartCreation extends BaseTest {

	@Test(groups={"parallel"})
	void create_new_cart_for_user() throws IOException
	{
		//Response response1 = UserClient.getInstance().createUser();
		 Response response = UserClient.getInstance().CreateCart(BaseTest.getUserEmail());
		
	System.out.println("hhhhhhhhhhh======");
			response.prettyPrint();
			  Assertions.assertResponseNotEmpty(response);
	        
			 assertSuccessStatusCode(response,201 , "expected 201 response ");
			 assertTextForStatusCode(response,"Created" , "expected Created text response ");
			 
   
	}
	
	@Test
	void cart_already_exists_for_user() throws IOException
	{
		String Email = BaseTest.getUserEmail();
		//Response response1 = UserClient.getInstance().createUser();
		 Response response = UserClient.getInstance().CreateCart(Email);
		 response.prettyPrint();
		  Assertions.assertResponseNotEmpty(response);
		CartRequest UR = response.as(CartRequest.class)	; 
		String CreatedUser = UR.getUser_id();
		System.out.println("piku "+CreatedUser);
		
		//now use same email to create cart
		
		//Response response1 = UserClient.getInstance().createUser();
		 Response responseForSameEmail = UserClient.getInstance().CreateCart(Email);
		 responseForSameEmail.prettyPrint();
		
		 String expectedMsg = responseForSameEmail.jsonPath().get("error");
		 assertExpectedAndActual(response, expectedMsg, "missing email or phone");
	
	}
	
}
