package piku_mau;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilies.AssertionsUtil;
import utilies.BaseTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class HealthCheck extends BaseTest {
	private static final Logger logger = LogManager.getLogger(HealthCheck.class);
	@Test
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
