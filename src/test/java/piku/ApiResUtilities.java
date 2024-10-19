package piku;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.contains;
import static org.testng.Assert.assertNotNull;

import org.hamcrest.Matchers;

import io.restassured.response.Response;

public class ApiResUtilities {

	 public static void assertValueNotNull(Response response, String Path) {
	       
	        assertNotNull("User ID should be present in the response", response.jsonPath().get(Path));
	 
	 }
	
  
    
    

    
    public static void assertResponseContains(Response response, String expectedValue, String customMessage) {
        assertThat(customMessage, response.body().asString(), containsString(expectedValue));
    }

    public static void assertResponseEquals(Response response, String expectedValue, String customMessage) {
        assertThat(customMessage, response.body().asString(), equalTo(expectedValue));
    }

    public static void assertHeaderValue(Response response, String headerName, String expectedValue) {
        assertThat("Unexpected header value" +response.header(headerName), response.header(headerName), equalTo(expectedValue));
    }

  
    
    public static void assertResponseBodyHasProperty(Response response, String jsonPath) {
        boolean isPresent = response.jsonPath().get(jsonPath) != null;
        assertThat("Expected response body to have property: " + jsonPath, isPresent, equalTo(true));
    }






	public static void assertSuccessStatusCode(Response response, int Stauscode, String message) {
		// TODO Auto-generated method stub
		  assertThat(message, response.getStatusCode(), equalTo(Stauscode));
	}






	public static void assertTextForStatusCode(Response response, String ExpectedtText, String message) {
		// TODO Auto-generated method stub
		assertThat(message, response.getStatusLine(), containsString(ExpectedtText));
		
	}
}