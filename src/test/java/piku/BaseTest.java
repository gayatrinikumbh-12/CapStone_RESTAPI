package piku;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import utilities.PropertyUtils;

public class BaseTest {

	
	@BeforeClass
	void setBaseURI()
	{
		String baseUrl = PropertyUtils.getProperty("base.url");
        
		RestAssured.baseURI = baseUrl;
		System.out.println(RestAssured.baseURI );

}
}