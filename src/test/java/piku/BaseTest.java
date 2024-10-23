package piku;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import data.TestDataBuild;

import static org.testng.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.PropertyUtils;

public class BaseTest {

	
	@BeforeClass(groups = {"e2e", "parallel"})
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
	
	
	@AfterMethod
	  protected void afterTestMethod() {
	      TestDataBuild.clearThreadLocal(); // You will need to create this method to clear the thread-local variable.
	  }
	
	protected void assertStatusCode(Response response, int expectedStatusCode) {
	    assertThat("Expected status code " + expectedStatusCode + ", but was " + response.getStatusCode(),
	               response.getStatusCode(), equalTo(expectedStatusCode));
	}

	protected void logResponseBody(Response response) {
	    response.then().log().body();
	}
	
	

}