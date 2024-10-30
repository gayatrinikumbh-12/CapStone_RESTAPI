package utilies;

import static org.testng.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.notNullValue;

public class Assertions {

	public static <T> void assertResponseWithCustomMessage(Response response, String jsonPath, T expectedValue,
	        String errorMessage) {
	    T actualValue = response.jsonPath().get(jsonPath);
	    assertThat(String.format("%s: Expected value at JSON path '%s' to be: '%s', but got: '%s'", 
	                             errorMessage, jsonPath, expectedValue, actualValue),
	               actualValue, equalTo(expectedValue));
	}

	public static <T> void assertPropertyNotNull(Response response, String jsonPath, String errorMessage) {
	    T actualValue = response.jsonPath().get(jsonPath);
	    assertThat(String.format("%s: Expected JSON path '%s' to not be null, but it was null.", 
	                             errorMessage, jsonPath),
	               actualValue, notNullValue());
	}

	public static void assertStatusCode(Response response, int expectedStatusCode) {
	    int actualStatusCode = response.getStatusCode();
	    assertThat(String.format("Expected status code: '%d', but got: '%d'", 
	                             expectedStatusCode, actualStatusCode),
	               actualStatusCode, equalTo(expectedStatusCode));
	}

	public static void assertHeader(Response response, String headerName, String expectedHeaderValue) {
	    String actualHeaderValue = response.getHeader(headerName);
	    assertThat(String.format("Expected header '%s' to be: '%s', but got: '%s'", 
	                             headerName, expectedHeaderValue, actualHeaderValue),
	               actualHeaderValue, equalTo(expectedHeaderValue));
	}

	public static <T> void assertJsonPathValue(Response response, String jsonPath, T expectedValue) {
	    T actualValue = response.jsonPath().get(jsonPath);
	    assertThat(String.format("Expected JSON path '%s' to return: '%s', but got: '%s'", 
	                             jsonPath, expectedValue, actualValue),
	               actualValue, equalTo(expectedValue));
	}

	public static void assertJsonPathExists(Response response, String jsonPath) {
	    Object actualValue = response.jsonPath().get(jsonPath);
	    assertThat(String.format("Expected JSON path '%s' to exist, but it was null.", 
	                             jsonPath),
	               actualValue, notNullValue());
	}

	public static void assertResponseNotEmpty(Response response) {
	    String responseBody = response.getBody().asString();
	    assertThat("Expected response body to be non-empty, but it was empty.", 
	               responseBody, not(isEmptyString()));
	}

	public static void assertJsonPathIsEmpty(Response response, String jsonPath) {
	    Object actualValue = response.jsonPath().get(jsonPath);
	    assertThat(String.format("Expected JSON path '%s' to be empty, but got: '%s'", 
	                             jsonPath, actualValue),
	                is(emptyOrNullString()));
	}

	public static void assertListSize(Response response, String jsonPath, int expectedSize) {
	    List<?> actualList = response.jsonPath().getList(jsonPath);
	    assertThat(String.format("Expected JSON path '%s' to have size: '%d', but got: '%s'", 
	                             jsonPath, expectedSize, (actualList != null ? actualList.size() : "null")),
	               actualList, hasSize(expectedSize));
	}

	public static void assertJsonStructure(Response response, String jsonPath, Map<String, Object> expectedStructure) {
	    Map<String, Object> actualStructure = response.jsonPath().get(jsonPath);
	    assertThat(String.format("Expected JSON structure at path '%s' to match: '%s', but got: '%s'", 
	                             jsonPath, expectedStructure, actualStructure),
	               actualStructure, is(expectedStructure));
	}

	public static void assertResponseTimeLessThan(Response response, long maxResponseTime) {
	    long actualResponseTime = response.getTime();
	    assertThat(String.format("Expected response time to be less than '%d' ms, but got: '%d' ms", 
	                             maxResponseTime, actualResponseTime),
	               actualResponseTime, lessThan(maxResponseTime));
	}

	public static void assertResponseFieldCondition(Response response, String jsonPath, String condition, String errorMessage) {
	    Object actualValue = response.jsonPath().get(jsonPath);
	    assertThat(String.format("%s: Expected JSON path '%s' to have property '%s'", 
	                             errorMessage, jsonPath, condition),
	               actualValue, Matchers.hasProperty(condition));
	}
    
    public static void assertResponseField(Response response, String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        assertThat(String.format("Expected '%s' to be '%s' but was '%s'", jsonPath, expectedValue, actualValue),
                   actualValue, equalTo(expectedValue));
    }
    
    public static void assertStatusCodeWithReason(Response response, int expectedStatusCode, String reason) {
        int actualStatusCode = response.getStatusCode();
        assertThat(String.format("Expected status code '%d' but received '%d'. Reason: %s", expectedStatusCode,
                                 actualStatusCode, reason), actualStatusCode, equalTo(expectedStatusCode));
    }
}
