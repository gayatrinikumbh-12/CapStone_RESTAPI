package utilies;

import static org.testng.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.notNullValue;

public class Assertions {

	public static <T> void assertResponseWithCustomMessage(Response response, String jsonPath, T expectedValue,
			String errorMessage) {
		T actualValue = response.jsonPath().get(jsonPath);
		assertThat(errorMessage + ": Expected value at JSON path '" + jsonPath + "' to be: " + expectedValue
				+ ", but got: " + actualValue, actualValue, equalTo(expectedValue));
	}

	public static <T> void assertPropertyNotNull(Response response, String jsonPath, String errorMessage) {
		T actualValue = response.jsonPath().get(jsonPath);
		assertThat(errorMessage + ": Expected JSON path '" + jsonPath + "' to not be null, but it was null.",
				actualValue, notNullValue());
	}

	public static void assertStatusCode(Response response, int expectedStatusCode) {
		int actualStatusCode = response.getStatusCode();
		assertThat("Expected status code: " + expectedStatusCode + ", but got: " + actualStatusCode, actualStatusCode,
				equalTo(expectedStatusCode));
	}

	public static void assertHeader(Response response, String headerName, String expectedHeaderValue) {
		String actualHeaderValue = response.getHeader(headerName);
		assertThat("Expected header '" + headerName + "' to be: " + expectedHeaderValue + ", but got: "
				+ actualHeaderValue, actualHeaderValue, equalTo(expectedHeaderValue));
	}

	public static <T> void assertJsonPathValue(Response response, String jsonPath, T expectedValue) {
		T actualValue = response.jsonPath().get(jsonPath);
		assertThat("Expected JSON path '" + jsonPath + "' to return: " + expectedValue + ", but got: " + actualValue,
				actualValue, equalTo(expectedValue));
	}

	public static void assertJsonPathExists(Response response, String jsonPath) {
		Object actualValue = response.jsonPath().get(jsonPath);
		assertThat("Expected JSON path '" + jsonPath + "' to exist, but it was null.", actualValue, notNullValue());
	}

	public static void assertResponseNotEmpty(Response response) {
		String responseBody = response.getBody().asString();
		assertThat("Expected response body to be non-empty, but it was empty.", responseBody, not(isEmptyString()));
	}
	
	
	public static void assertJsonPathIsEmpty(Response response, String jsonPath) {
        Object actualValue = response.jsonPath().get(jsonPath);
        assertThat("Expected JSON path '" + jsonPath + "' to be empty, but got: " + actualValue,
                    is(emptyOrNullString()));
    }

    public static void assertListSize(Response response, String jsonPath, int expectedSize) {
        List<?> actualList = response.jsonPath().getList(jsonPath);
        assertThat("Expected JSON path '" + jsonPath + "' to have size: " + expectedSize +
                   ", but got: " + (actualList != null ? actualList.size() : "null"),
                   actualList, hasSize(expectedSize));
    }

    public static void assertJsonStructure(Response response, String jsonPath, Map<String, Object> expectedStructure) {
        Map<String, Object> actualStructure = response.jsonPath().get(jsonPath);
        assertThat("Expected JSON structure at path '" + jsonPath + "' to match: " + expectedStructure +
                   ", but got: " + actualStructure,
                   actualStructure, is(expectedStructure));
    }

    public static void assertResponseTimeLessThan(Response response, long maxResponseTime) {
        long actualResponseTime = response.getTime();
        assertThat("Expected response time to be less than " + maxResponseTime + "ms, but got: " + actualResponseTime + "ms",
                   actualResponseTime, lessThan(maxResponseTime));
    }
}
