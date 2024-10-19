package clients;





import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserSignupRequest;
import utilities.EndpointConfig;

import utilities.PropertyUtils;
import utilities.RandomNumberGenrator;


public class UserClient  {
	
    private static final UserClient INSTANCE = new UserClient();
   // String baseUrl = PropertyUtils.getProperty("base.url");
    
    private UserClient() {
        // Private constructor to prevent direct instantiation
    }

    public static UserClient getInstance() {
        return INSTANCE;
    }

    
   
    public static Response createUser() {

    	
        RandomNumberGenrator RG = new RandomNumberGenrator();
        String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");
        String randomEmail = RG.RandomEmailGenrator();

        UserSignupRequest signupRequestModel = UserSignupRequest.builder().email(randomEmail).password("123456")
				.build();
       

        // Send POST request and capture the response
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(signupRequestModel)
                .post(signUpEndpoint);
        
        response.prettyPrint();
        
       
	
        return response;
    }
    
    public Response authenticateUser() {

    	
        //RandomNumberGenrator RG = new RandomNumberGenrator();
        //UserClient uc = UserClient.getInstance();
        String email =UserClient.createUser().jsonPath().get("data.user.email");
    
        String LoginEndpoint = EndpointConfig.getEndpoint("loginProfile", "Login");
        UserSignupRequest LoginRequest = UserSignupRequest.builder()
			    .email(email).password("123456")
				.build();
       

        // Send POST request and capture the response
        Response LoginResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(LoginRequest)
                .post(LoginEndpoint);
        
        LoginResponse.prettyPrint();
		return LoginResponse;

       
    }


    


}