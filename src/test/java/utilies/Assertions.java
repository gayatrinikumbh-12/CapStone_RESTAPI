package utilies;


import static org.testng.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.notNullValue;

public class Assertions {

	
	 public static void assertResponseWithCustomMessage(Response response, String jsonPath, Object expectedValue, String errorMessage) {
	        assertThat(errorMessage, response.jsonPath().get(jsonPath), equalTo(errorMessage));
	    }

	    // Example of reusable method for checking if a property is not null
	    public static void assertPropertyNotNull(Response response, String jsonPath, String errorMessage) {
	        assertNotNull(response.jsonPath().get(jsonPath), errorMessage);
	    }
	    
	    
	    /**
	     * Asserts that the response status code matches the expected status code.
	     *
	     * @param response          The API response to validate.
	     * @param expectedStatusCode The expected HTTP status code.
	     */
	    public static void assertStatusCode(Response response, int expectedStatusCode) {
	        assertThat("Expected status code: " + expectedStatusCode + ", but got: " + response.getStatusCode(),
	                   response.getStatusCode(), equalTo(expectedStatusCode));
	    }

	    /**
	     * Asserts that a specific header in the response matches the expected value.
	     *
	     * @param response          The API response to validate.
	     * @param headerName        The name of the header to check.
	     * @param expectedHeaderValue The expected value of the header.
	     */
	    public static void assertHeader(Response response, String headerName, String expectedHeaderValue) {
	        String actualHeaderValue = response.getHeader(headerName);
	        assertThat("Expected header '" + headerName + "' to be: " + expectedHeaderValue + 
	                   ", but got: " + actualHeaderValue,
	                   actualHeaderValue, equalTo(expectedHeaderValue));
	    }

	    /**
	     * Asserts that a specific JSON path in the response returns the expected value.
	     *
	     * @param response    The API response to validate.
	     * @param jsonPath    The JSON path expression to evaluate.
	     * @param expectedValue The expected value at the specified JSON path.
	     */
	    public static void assertJsonPathValue(Response response, String jsonPath, Object expectedValue) {
	        Object actualValue = response.jsonPath().get(jsonPath);
	        assertThat("Expected JSON path '" + jsonPath + "' to return: " + expectedValue + 
	                   ", but got: " + actualValue,
	                   actualValue, equalTo(expectedValue));
	    }

	    /**
	     * Asserts that a specific JSON path exists in the response.
	     *
	     * @param response The API response to validate.
	     * @param jsonPath The JSON path expression to check for existence.
	     */
	    public static void assertJsonPathExists(Response response, String jsonPath) {
	        Object actualValue = response.jsonPath().get(jsonPath);
	        assertThat("Expected JSON path '" + jsonPath + "' to exist, but it was null.",
	                   actualValue, notNullValue());
	    }

	    /**
	     * Asserts that the response body is not empty.
	     *
	     * @param response The API response to validate.
	     */
	    public static void assertResponseNotEmpty(Response response) {
	        String responseBody = response.getBody().asString();
	        assertThat("Expected response body to be non-empty, but it was empty.",
	                   responseBody, not(isEmptyString()));
	    }
}
