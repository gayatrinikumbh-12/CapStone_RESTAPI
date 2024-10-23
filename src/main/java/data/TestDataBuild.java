package data;
import clients.UserClient;
import models.UserSignupRequest;
import utilities.EndpointConfig;
import utilities.RandomNumberGenrator;

public class TestDataBuild {

	
	public static UserSignupRequest payloadUserSignUP()
	{
		RandomNumberGenrator RG = new RandomNumberGenrator();
        String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");
        String randomEmail = RG.RandomEmailGenrator();

        UserSignupRequest signupRequestModel = UserSignupRequest.builder().email(randomEmail).password("123456")
				.build();
        return signupRequestModel;
       
	}
	
	public static UserSignupRequest payloadLogin()
	{
		 String email =UserClient.getInstance().createUser().jsonPath().get("data.user.email");
		 UserSignupRequest LoginRequest = UserSignupRequest.builder()
				    .email(email).password("123456")
					.build();

        return LoginRequest;
       
	}
}
