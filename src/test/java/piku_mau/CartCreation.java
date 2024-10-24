package piku_mau;

import java.io.IOException;

import org.testng.annotations.Test;

import clients.UserClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserSignupResponse;
import piku_mau.ApiResUtilities;
import piku_mau.BaseTest;
import piku_mau.SignUPTest;

public class CartCreation extends BaseTest {

	@Test(groups={"parallel"})
	void create_new_cart_for_user() throws IOException
	{
		//Response response1 = UserClient.getInstance().createUser();
		 Response response = UserClient.getInstance().CreateCart(BaseTest.getUserEmail());
		
	System.out.println("hhhhhhhhhhh======");
			response.prettyPrint();
	        
			 ApiResUtilities.assertSuccessStatusCode(response,201 , "expected 201 response ");
			 ApiResUtilities.assertTextForStatusCode(response,"Created" , "expected Created text response ");
			 
    }
	
}
