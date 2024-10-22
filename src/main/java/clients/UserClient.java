package clients;





import data.TestDataBuild;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserSignupRequest;
import models.UserSignupResponse;
import utilities.ApiResponseDeserializer;
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

    	
       
        String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");
       

        
       
        	 UserSignupRequest payloadSignUP = TestDataBuild.payloadUserSignUP();
        // Send POST request and capture the response
        Response response = RestAssured.given()
        		.contentType(ContentType.JSON)
                .body(payloadSignUP)
                .post(signUpEndpoint);
        
        response.prettyPrint();
        System.out.println(" piku   "+response.getHeaders());
       // UserSignupResponse userSignupResponse = ApiResponseDeserializer.deserializeResponse(loginResponse, UserSignupResponse.class);
	
        return response;
    }
    
    
    
    
    public Response authenticateUser() {

    	
        //RandomNumberGenrator RG = new RandomNumberGenrator();
        //UserClient uc = UserClient.getInstance();
        String email =UserClient.createUser().jsonPath().get("data.user.email");
    
        String LoginEndpoint = EndpointConfig.getEndpoint("loginProfile", "Login");
       
        UserSignupRequest LoginRequest = TestDataBuild.payloadLogin();

        // Send POST request and capture the response
        Response LoginResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(LoginRequest)
                .post(LoginEndpoint);
        UserSignupResponse userSignupResponse = ApiResponseDeserializer.deserializeResponse(LoginResponse, UserSignupResponse.class);
       System.out.println("userSignupResponse  "+userSignupResponse);
       
        return LoginResponse;

       
    }

    
    


}