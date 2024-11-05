package piku_mau;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilies.AssertionsUtil;
import utilies.BaseTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

@Epic("Epic - 01")
@Feature("HealthCheck for system ")
public class HealthCheck extends BaseTest {
	private static final Logger logger = LogManager.getLogger(HealthCheck.class);
	
	@Story("Story 1 - healthcheck")
	@Test(description ="shouldReturnOKForHealthCheck test")
	@Description("check is URL is UP and Running")
	@Severity(SeverityLevel.BLOCKER)
 	void shouldReturnOKForHealthCheck() {
		 logger.info("Starting 'shouldReturnOKForHealthCheck' test case");
		String uri = "/health-check";
		Response response = RestAssured.given().contentType(ContentType.JSON).get(uri);
		logger.info("Response received with status code: {}", response.getStatusCode());
		response.prettyPrint();
		AssertionsUtil.assertResponseNotEmpty(response);
		AssertionsUtil.assertStatusCode(response,200);
		logger.info("'shouldReturnOKForHealthCheck' has been successfully created.", userEmail);
	}
}
