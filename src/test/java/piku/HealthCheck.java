package piku;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.PropertyUtils;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
public class HealthCheck extends BaseTest{

	@Test
	void shouldReturnOKForHealthCheck()
	{
        
		String uri = "/health-check";
	Response Res = RestAssured.given()
			        .contentType(ContentType.JSON)
					.get(uri);
	///System.out.println("knkmk  "+Res);
	
	//int statusCode = Res.getStatusCode();
	//String statusText = Res.getStatusLine();
	ApiResUtilities.assertSuccessStatusCode(Res);
	ApiResUtilities.assertTextForCreatedStatusCode(Res);
	//assertThat(statusCode, Matchers.equalTo(200));
//	assertThat("Health check status text is incorrect", statusText, containsString("OK"));
	//assertThat(statusText, Matchers.contains("ok") != null);
	}
}
