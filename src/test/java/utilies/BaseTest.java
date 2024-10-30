package utilies;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertNotNull;

import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import utilities.PropertyUtils;
import utilities.RandomNumberGenrator;

public class BaseTest {

	protected static ThreadLocal<String> userEmail = new ThreadLocal<>();

	@BeforeClass
	void setBaseURI() {
		String baseUrl = PropertyUtils.getProperty("base.url");

		RestAssured.baseURI = baseUrl;
		System.out.println(RestAssured.baseURI);

	}

	@AfterClass
	public void tearDown() {
		// Code to be executed after all tests

	}

	@BeforeMethod
	protected void beforeTestMesthod() {

		RandomNumberGenrator RG = new RandomNumberGenrator();
		String randomEmail = RG.randomEmail();
		userEmail.set(randomEmail);

	}

	public static String getUserEmail() {
		return userEmail.get();
	}

	@AfterMethod(groups = { "Login" })
	protected void afterTestMethod() {

		userEmail.remove();

	}

	protected void assertStatusCode(Response response, int expectedStatusCode) {
		assertThat("Expected status code " + expectedStatusCode + ", but was " + response.getStatusCode(),
				response.getStatusCode(), equalTo(expectedStatusCode));
	}

	protected void logResponseBody(Response response) {
		response.then().log().body();
	}

	protected static void assertValueNotNull(Response response, String Path) {

		assertNotNull("User ID should be present in the response", response.jsonPath().get(Path));

	}

	protected static void assertResponseContains(Response response, String expectedValue, String customMessage) {
		assertThat(customMessage, response.body().asString(), containsString(expectedValue));
	}

	protected static void assertResponseEquals(Response response, String expectedValue, String customMessage) {
		assertThat(customMessage, response.body().asString(), equalTo(expectedValue));
	}

	protected static void assertHeaderValue(Response response, String headerName, String expectedValue) {
		assertThat("Unexpected header value" + response.header(headerName), response.header(headerName),
				equalTo(expectedValue));
	}

	protected static void assertResponseBodyHasProperty(Response response, String jsonPath) {
		boolean isPresent = response.jsonPath().get(jsonPath) != null;
		assertThat("Expected response body to have property: " + jsonPath, isPresent, equalTo(true));
	}

	protected static void assertSuccessStatusCode(Response response, int Stauscode, String message) {
		// TODO Auto-generated method stub
		assertThat(message, response.getStatusCode(), equalTo(Stauscode));
	}

	protected static void assertTextForStatusCode(Response response, String ExpectedtText, String message) {
		// TODO Auto-generated method stub
		assertThat(message, response.getStatusLine(), containsString(ExpectedtText));

	}

	protected static void assertExpectedAndActual(Response response, String expectedMsg, String actual) {
		assertThat(expectedMsg, Matchers.equalTo(actual));
	}

}