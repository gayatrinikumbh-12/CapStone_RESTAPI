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
	
  
    
    public static void assertSuccessStatusCode(Response response) {
        assertThat("Expected a success status code of 200, but received: " + response.statusCode(), response.statusCode(), equalTo(200));
    }
    
    public static void assertTextForCreatedStatusCode(Response response) {
        assertThat("Expected a  Text code of 201 but received ", response.getStatusLine(), Matchers.containsString("OK"));
    }
    
    public static void assertCreatedStatusCode(Response response) {
        assertThat("Expected a Created status code of 201 but received :"+  response.statusCode(), response.statusCode(), Matchers.equalTo(201));
    }

    public static void assertBadRequestStatusCode(Response response) {
        assertThat("Expected a Bad Request code of 400 but received " + response.statusCode(), response.statusCode(), equalTo(400));
    }

    public static void assertUnauthorizedStatusCode(Response response) {
        assertThat(" Expected UnauthorizedStatus Code of 401 but recived " + response.statusCode(), response.statusCode(), equalTo(401));
    }

    // ... other status code assertions

   
    
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
}