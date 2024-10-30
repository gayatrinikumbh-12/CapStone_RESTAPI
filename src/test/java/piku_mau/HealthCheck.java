package piku_mau;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilies.Assertions;
import utilies.BaseTest;
import utilities.PropertyUtils;

import org.testng.annotations.Test;

public class HealthCheck extends BaseTest {

	@Test
 	void shouldReturnOKForHealthCheck() {
		
		String uri = "/health-check";
		Response response = RestAssured.given().contentType(ContentType.JSON).get(uri);
		response.prettyPrint();
		Assertions.assertResponseNotEmpty(response);
		Assertions.assertStatusCode(response,200);
		Assertions.assertResponseWithCustomMessage(response, "message" ,"ok", "Expected healthCheck  message is NOT displayed");
	}
}
