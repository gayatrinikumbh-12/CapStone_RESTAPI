package data;
import clients.UserClient;
import models.UserSignupRequest;
import utilities.EndpointConfig;
import utilities.RandomNumberGenrator;

public class TestDataBuild {

	 private static final ThreadLocal<String> userEmail = new ThreadLocal<>();

	public static UserSignupRequest payloadUserSignUP()
	{
		RandomNumberGenrator RG = new RandomNumberGenrator();
        String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");
        String randomEmail = RG.randomEmail();
        userEmail.set(randomEmail);
        UserSignupRequest signupRequestModel = UserSignupRequest.builder().email(randomEmail).password("123456")
				.build();
        return signupRequestModel;
       
	}
	
	public static UserSignupRequest payloadLogin()
	{
		// String email =UserClient.getInstance().createUser().jsonPath().get("data.user.email");
		 UserSignupRequest LoginRequest = UserSignupRequest.builder()
				    .email(userEmail.get()).password("123456")
					.build();

        return LoginRequest;
       
	}
	
	public static void clearThreadLocal()
	{
		clearThreadLocal();
	}
}
