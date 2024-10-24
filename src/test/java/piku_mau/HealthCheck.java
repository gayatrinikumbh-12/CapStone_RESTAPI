package piku_mau;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.PropertyUtils;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
public class HealthCheck extends BaseTest{

	@Step("Check is Application is Up and Running")
	@Test(groups={"parallel"})
	void shouldReturnOKForHealthCheck()
	{
        
		String uri = "/health-check";
	Response Res = RestAssured.given()
			        .contentType(ContentType.JSON)
					.get(uri);
	///System.out.println("knkmk  "+Res);
	
	//int statusCode = Res.getStatusCode();
	//String statusText = Res.getStatusLine();
	ApiResUtilities.assertSuccessStatusCode(Res,200 , "expected 200 response ");
	ApiResUtilities.assertTextForStatusCode(Res,"OK","expected status test as ok");
	//assertThat(statusCode, Matchers.equalTo(200));
//	assertThat("Health check status text is incorrect", statusText, containsString("OK"));
	//assertThat(statusText, Matchers.contains("ok") != null);
	}
}
