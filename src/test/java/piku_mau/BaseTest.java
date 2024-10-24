package piku_mau;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import data.TestDataBuild;

import static org.testng.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.PropertyUtils;
import utilities.RandomNumberGenrator;

public class BaseTest {

	 
	 protected static  ThreadLocal<String> userEmail = new ThreadLocal<>();
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
	@BeforeMethod
	 protected void beforeTestMesthod()
	 {
		 
		 RandomNumberGenrator RG = new RandomNumberGenrator();
		 String randomEmail = RG.randomEmail();
		 userEmail.set(randomEmail);
		 
	 }
	
	public static String getUserEmail() {
		 return userEmail.get();
    }
	
	@AfterMethod
	  protected void afterTestMethod() {
	    
		
			 userEmail.remove();
		
	  }
	
	protected void assertStatusCode(Response response, int expectedStatusCode) {
	    assertThat("Expected status code " + expectedStatusCode + ", but was " + response.getStatusCode(),
	               response.getStatusCode(), equalTo(expectedStatusCode));
	}

	protected void logResponseBody(Response response) {
	    response.then().log().body();
	}
	
	

}