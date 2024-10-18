package piku;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import static org.testng.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.PropertyUtils;

public class BaseTest {

	
	@BeforeClass
	void setBaseURI()
	{
		String baseUrl = PropertyUtils.getProperty("base.url");
        
		RestAssured.baseURI = baseUrl;
		System.out.println(RestAssured.baseURI );

     }
	
	@AfterClass
    public void tearDown() {
        // Code to be executed after all tests
		
    }
	
	@AfterTest
	protected void assertExpectedStatusCode(int expectedStatusCode, Response response) {
	    assertThat("Unexpected status code", response.getStatusCode(), equalTo(expectedStatusCode));
	}

	@AfterTest
	protected void logResponse(Response response) {
	    response.then().log().all();
	}
}