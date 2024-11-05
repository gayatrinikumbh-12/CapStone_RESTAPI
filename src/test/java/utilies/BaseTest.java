package utilies;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

import static org.testng.Assert.assertNotNull;

import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import piku_mau.SignUPTest;

import java.io.PrintWriter;
import utilities.PropertyUtils;
import utilities.RandomNumberGenrator;

public abstract class BaseTest {

	protected static ThreadLocal<String> userEmail = new ThreadLocal<>();
	protected static ThreadLocal<String> userPassword = new ThreadLocal<>();
	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	
	@BeforeClass
	public void setBaseURI() {
		logger.info("Test case initiated for /s: {}", this.getClass().toString());
		RestAssured.reset(); // Add this to reset RestAssured configuration before each test
		String baseUrl = PropertyUtils.getProperty("base.url");
		System.out.println(baseUrl);
		RestAssured.baseURI = baseUrl;
		

	}

	@AfterClass
	public void tearDown() {
		// Code to be executed after all tests
		
	}
	

	@BeforeMethod
	protected void beforeTestMesthod() {
		logger.info("[{}] - [{}] - {}", this.getClass().getSimpleName(), Thread.currentThread().getName(), "Starting Test Case ");
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		//RandomNumberGenrator RG = new RandomNumberGenrator();
		//String randomEmail = RG.randomEmail();
		
		/*
		Faker faker = new Faker();
	    String email_f = faker.internet().emailAddress();
	    String password_f = faker.internet().password(8, 16);
		userEmail.set(email_f);
		userPassword.set(password_f);
		*/
	}
	
	@AfterMethod
	protected void AfterTestMesthod(ITestResult result) {

		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			Throwable t = result.getThrowable();
			StringWriter error = new StringWriter();
			t.printStackTrace(new PrintWriter(error));
			logger.info(error.toString());
		
		logger.error("Test case '{}' failed with error: {}", result.getMethod().getMethodName(), error.toString());
		}
		logger.debug("Reviewing logs for test case '{}' to ensure diagnostic details are captured.", result.getMethod().getMethodName());
	}
	
	
	public static String getUserEmail() {
		return userEmail.get();
	}
	
	public static String getuserPassword() {
		return userPassword.get();
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