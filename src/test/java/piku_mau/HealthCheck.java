package piku_mau;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilies.AssertionsUtil;
import utilies.BaseTest;
import utilities.PropertyUtils;

import org.testng.annotations.Test;

public class HealthCheck extends BaseTest {

	@Test
 	void shouldReturnOKForHealthCheck() {
		
		String uri = "/health-check";
		Response response = RestAssured.given().contentType(ContentType.JSON).get(uri);
		response.prettyPrint();
		AssertionsUtil.assertResponseNotEmpty(response);
		AssertionsUtil.assertStatusCode(response,200);
		
	}
}
