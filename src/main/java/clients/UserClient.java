package clients;

import data.TestDataBuild;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserSignupRequest;
import models.UserSignupResponse;
import utilities.ApiResponseDeserializer;
import utilities.EndpointConfig;

public class UserClient {

	private static final UserClient INSTANCE = new UserClient();
	// protected static ThreadLocal<String> userEmail = new ThreadLocal<>();
	// String baseUrl = PropertyUtils.getProperty("base.url");

	private UserClient() {
		// Private constructor to prevent direct instantiation
	}

	public static UserClient getInstance() {
		return INSTANCE;
	}

	public Response createUser(String Email) {

		String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");

		UserSignupRequest payloadSignUP = TestDataBuild.payloadUserSignUP(Email);
		// Send POST request and capture the response
		Response response = RestAssured.given().contentType(ContentType.JSON).body(payloadSignUP).post(signUpEndpoint);

		response.prettyPrint();
		// System.out.println(" piku "+response.getHeaders());
		// UserSignupResponse userSignupResponse =
		// ApiResponseDeserializer.deserializeResponse(loginResponse,
		// UserSignupResponse.class);

		return response;
	}

	public Response authenticateUser(String Email) {

		// RandomNumberGenrator RG = new RandomNumberGenrator();
		// UserClient uc = UserClient.getInstance();
		// String email
		// =UserClient.getInstance().createUser().jsonPath().get("data.user.email");

		String LoginEndpoint = EndpointConfig.getEndpoint("loginProfile", "Login");

		UserSignupRequest LoginRequest = TestDataBuild.payloadLogin(Email);
		System.out.println(LoginRequest.toString());
		// Send POST request and capture the response
		Response LoginResponse = RestAssured.given().contentType(ContentType.JSON).body(LoginRequest)
				.post(LoginEndpoint);
		LoginResponse.prettyPrint();
		System.out.println("?????? " + LoginResponse);
		UserSignupResponse userSignupResponse = ApiResponseDeserializer.deserializeResponse(LoginResponse,
				UserSignupResponse.class);
		System.out.println("userSignupResponse  " + userSignupResponse);

		return LoginResponse;

	}

	public Response CreateCart(String Email) {

		Response Response = UserClient.INSTANCE.authenticateUser(Email);
		UserSignupResponse UR = Response.as(UserSignupResponse.class);
		String Access_Token = UR.getData().getSession().getAccessToken();

		String CreateCart = EndpointConfig.getEndpoint("cart", "createCart");
		// Response Cart_Creation_response = null;

		// Add cart
		Response Cart_Creation_response = RestAssured.given().header("Authorization", "Bearer " + Access_Token)
				.contentType(ContentType.JSON).post(CreateCart);
		// Cart_Creation_response.prettyPrint();

		return Cart_Creation_response;

	}

}