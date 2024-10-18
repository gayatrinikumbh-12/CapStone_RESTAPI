package clients;





import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import utilities.EndpointConfig;
import utilities.LoginRequestModel;
import utilities.PropertyUtils;
import utilities.RandomNumberGenrator;
import utilities.SignupRequestModel;

public class UserClient  {
    private static final UserClient INSTANCE = new UserClient();
    String baseUrl = PropertyUtils.getProperty("base.url");
    
    private UserClient() {
        // Private constructor to prevent direct instantiation
    }

    public static UserClient getInstance() {
        return INSTANCE;
    }

    

    public Response Verify_SignUPTest() {

    	RestAssured.baseURI = baseUrl;
        RandomNumberGenrator RG = new RandomNumberGenrator();
        String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");
        String randomEmail = RG.RandomEmailGenrator();

        SignupRequestModel signupRequestModel = SignupRequestModel.builder().email(randomEmail).password("123456")
				.build();
       

        // Send POST request and capture the response
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(signupRequestModel)
                .post(signUpEndpoint);
        
        response.prettyPrint();
        
       
	
        return response;
    }
    
    public void Verify_LoginAPI() {

    	RestAssured.baseURI = baseUrl;
        RandomNumberGenrator RG = new RandomNumberGenrator();
        UserClient uc = new UserClient();
        String email =uc.Verify_SignUPTest().jsonPath().get("data.user.email");
    
        String LoginEndpoint = EndpointConfig.getEndpoint("loginProfile", "Login");
        LoginRequestModel LoginRequest = LoginRequestModel.builder()
			    .email(email).password("123456")
				.build();
       

        // Send POST request and capture the response
        Response LoginResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(LoginRequest)
                .post(LoginEndpoint);
        
        LoginResponse.prettyPrint();

       
    }
}