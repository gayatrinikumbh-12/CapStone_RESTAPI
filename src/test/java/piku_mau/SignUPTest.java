package piku_mau;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;

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

@Epic("Epic - 02")
@Feature("SignUPTest functionality ")
public class SignUPTest extends BaseTest {
	String email;
	private static final Logger logger = LogManager.getLogger(SignUPTest.class);

	
	
	@Story("Story 2 - SignUP")
	@Test(description ="shouldCreateNewUserSuccessfully test",dataProvider = "userDataProvider")
	@Description("check SignUP functionality")
	@Severity(SeverityLevel.CRITICAL)
	public void shouldCreateNewUserSuccessfully() throws IOException {
		 logger.info("Creating a new user with email: {}", BaseTest.getUserEmail());
		
		 userEmail.get();
		Response response = UserClient.getInstance().createUser(BaseTest.getUserEmail(), BaseTest.getuserPassword());
		logger.info("Response received with status code: {}", response.getStatusCode());
		UserSignupResponse userSignupResponse = response.as(UserSignupResponse.class);
				// Using the new assertion method within the POJO
		userSignupResponse.assertUserCreatedSuccessfully();

		Headers headers = response.getHeaders();
		assertNotNull(headers.get("Content-Type"), "Content-Type header should not be null");
		assertEquals(headers.getValue("Content-Type"), "application/json; charset=utf-8", "Unexpected Content-Type");
		//email = userSignupResponse.getData().getUser().getEmail();
		logger.info("User with email '{}' has been successfully created.", userEmail);
		
	}
	
	

}
