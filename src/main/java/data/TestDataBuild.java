package data;
import clients.UserClient;
import models.UserSignupRequest;
import utilities.EndpointConfig;
import utilities.RandomNumberGenrator; 

public class TestDataBuild {

	
	
	public static UserSignupRequest payloadUserSignUP(String Email)
	{
		
        String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");
      
        UserSignupRequest signupRequestModel = UserSignupRequest.builder().email(Email).password("123456")
				.build();
        return signupRequestModel;
       
	}
	
	
	public static UserSignupRequest payloadLogin(String Email)
	{
		String email =UserClient.getInstance().createUser(Email).jsonPath().get("data.user.email");
		 UserSignupRequest LoginRequest = UserSignupRequest.builder()
				    .email(email).password("123456")
					.build();

        return LoginRequest;
       
	}
	
	public static UserSignupRequest cart(String Email)
	{
		// String email =UserClient.getInstance().createUser().jsonPath().get("data.user.email");
		 UserSignupRequest LoginRequest = UserSignupRequest.builder()
				    .email(Email).password("123456")
					.build();

        return LoginRequest;
       
	}
	
	
}
