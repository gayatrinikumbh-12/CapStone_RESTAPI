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

	private UserClient() {
		// Private constructor to prevent direct instantiation
	}

	public static UserClient getInstance() {
		return INSTANCE;
	}

	public Response createUser(String Email, String Password) {

		String signUpEndpoint = EndpointConfig.getEndpoint("auth", "signUp");

		UserSignupRequest payloadSignUP = TestDataBuild.payloadUserSignUP(Email, Password);
		// Send POST request and capture the response
		Response response = RestAssured.given().contentType(ContentType.JSON).body(payloadSignUP).post(signUpEndpoint);
		response.prettyPrint();
		UserSignupResponse userSignupResponse = ApiResponseDeserializer.deserializeResponse(response,
				UserSignupResponse.class);
		response.prettyPrint();

		return response;
	}

	public Response authenticateUser(String Email, String Password) {

		String LoginEndpoint = EndpointConfig.getEndpoint("loginProfile", "Login");

		UserSignupRequest LoginRequest = TestDataBuild.payloadLogin(Email, Password);
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

	public Response CreateCart(String Email, String Password) {

		Response Response = UserClient.INSTANCE.authenticateUser(Email, Password);
		UserSignupResponse UR = Response.as(UserSignupResponse.class);
		String Access_Token = UR.getData().getSession().getAccessToken();

		String CreateCart = EndpointConfig.getEndpoint("cart", "createCart");

		// Add cart
		Response Cart_Creation_response = RestAssured.given().header("Authorization", "Bearer " + Access_Token)
				.contentType(ContentType.JSON).post(CreateCart);

		return Cart_Creation_response;

	}

	private UserSignupResponse deserializeResponse(Response response) {
		UserSignupResponse userResponse = new UserSignupResponse();
		userResponse.setStatusCode(response.getStatusCode());
		// userResponse.setHeaders(response.getHeaders());
		userResponse.setData(response.as(UserSignupResponse.Data.class)); // Deserialize the response body

		return userResponse;
	}

}