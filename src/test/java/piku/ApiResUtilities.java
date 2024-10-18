package piku;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotNull;

import io.restassured.response.Response;

public class ApiResUtilities {

	 public static void assertValueNotNull(Response response, String Path) {
	       
	        assertNotNull("User ID should be present in the response", response.jsonPath().get(Path));
	 
	 }
	
    public static void assertSuccessStatusCode(Response response) {
        assertThat("Unexpected status code", response.statusCode(), equalTo(200));
    }

    public static void assertCreatedStatusCode(Response response) {
        assertThat("Unexpected status code", response.statusCode(), equalTo(201));
    }

    public static void assertBadRequestStatusCode(Response response) {
        assertThat("Unexpected status code", response.statusCode(), equalTo(400));
    }

    public static void assertUnauthorizedStatusCode(Response response) {
        assertThat("Unexpected status code", response.statusCode(), equalTo(401));
    }

    // ... other status code assertions

    public static void assertResponseContains(Response response, String expectedValue) {
        assertThat("Response does not contain expected value", response.body().asString(), containsString(expectedValue));
    }

    public static void assertResponseEquals(Response response, String expectedValue) {
        assertThat("Response does not match expected value", response.body().asString(), equalTo(expectedValue));
    }

    public static void assertHeaderValue(Response response, String headerName, String expectedValue) {
        assertThat("Unexpected header value", response.header(headerName), equalTo(expectedValue));
    }

    // ... other header and payload assertions
    
    public static void assertResponseBodyHasProperty(Response response, String jsonPath) {
        boolean isPresent = response.jsonPath().get(jsonPath) != null;
        assertThat("Expected response body to have property: " + jsonPath, isPresent, equalTo(true));
    }
}