package piku_mau;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import clients.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import models.UserSignupResponse;
import utilies.AssertionsUtil;
import utilies.BaseTest;

@Epic("Epic - 03")
@Feature("Login functionality ")
public class LoginAPI extends BaseTest {
	
	@DataProvider
	public Object[][] userDataProvider() {
		Faker faker = new Faker();
	    String email_f = faker.internet().emailAddress();
	    String password_f = faker.internet().password(8, 16);
		userEmail.set(email_f);
		userPassword.set(password_f);
	    return new Object[][] {
	    	
	        {email_f, password_f}
	        
	    };
	}

	
	private static final Logger logger = LogManager.getLogger(LoginAPI.class);
	@Story("Story 3 - LogIn")
	@Test(description ="verifySuccessfulLoginWithValidCredentials test",dependsOnMethods = { "shouldCreateNewUserSuccessfully" })
	@Description("check LogIn functionality")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySuccessfulLoginWithValidCredentials(ITestContext context) {
		// Response response2 = UserClient.getInstance().createUser();
		 logger.info("Starting 'verifySuccessfulLoginWithValidCredentials' test case");
		 String em =(String) context.getAttribute("email");
		 String pa =(String) context.getAttribute("pass");
		Response response = UserClient.getInstance().authenticateUser(em,pa);
		logger.info("Response received with status code: {}", response.getStatusCode());
		System.out.println("login yes "+response.prettyPrint());
		UserSignupResponse userSignupResponse = response.as(UserSignupResponse.class);

		// Using the new assertion method within the POJO
		userSignupResponse.assertUserCreatedSuccessfully();
		AssertionsUtil.assertResponseNotEmpty(response);
		AssertionsUtil.assertStatusCode(response, 200);
		AssertionsUtil.assertPropertyNotNull(response, "data.session.access_token", "Access tocken is null");
		AssertionsUtil.assertPropertyNotNull(response, "data.user.email", "user email is null");

		Headers headers = response.getHeaders();

		assertNotNull(headers.get("Content-Type"), "Content-Type header should not be null");
		assertEquals(headers.getValue("Content-Type"), "application/json; charset=utf-8", "Unexpected Content-Type");
		logger.info("'verifySuccessfulLoginWithValidCredentials' has been successfully created.", userEmail);
	}
}