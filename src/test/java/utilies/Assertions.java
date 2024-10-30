package utilies;

import static org.testng.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.hamcrest.Description;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;

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

	public static <T> void assertResponseWithCustomMessage(Response response, String jsonPath, T expectedValue, String failureMessage) {
	    response.then().assertThat().body(jsonPath, new TypeSafeMatcher<T>() {
	        @Override
	        public void describeTo(Description description) {
	            description.appendText(failureMessage);
	        }

	        @Override
	        protected boolean matchesSafely(T item) {
	            return expectedValue.equals(item);
	        }
	    });
	}

	public static <T> void assertPropertyNotNull(Response response, String jsonPath, String errorMessage) {
	    response.then().assertThat().body(jsonPath, new TypeSafeMatcher<T>() {
	        @Override
	        public void describeTo(Description description) {
	            description.appendText(String.format("%s: Expected JSON path '%s' to not be null, but it was null.", 
	                                                 errorMessage, jsonPath));
	        }

	        @Override
	        protected boolean matchesSafely(T item) {
	            return item != null; // Check if the actual value is not null
	        }
	        
	        @Override
	        protected void describeMismatchSafely(T item, Description mismatchDescription) {
	            mismatchDescription.appendText("was null");
	        }
	    });
	}


	public static void assertStatusCode(Response response, int expectedStatusCode) {
	    response.then().assertThat().statusCode(expectedStatusCode);
	    
	    // Using a TypeSafeMatcher to provide a detailed failure message
	    response.then().assertThat().statusCode(new TypeSafeMatcher<Integer>() {
	        @Override
	        public void describeTo(Description description) {
	            description.appendText(String.format("Expected status code: '%d'", expectedStatusCode));
	        }

	        @Override
	        protected boolean matchesSafely(Integer actualStatusCode) {
	            return expectedStatusCode == actualStatusCode; // Compare expected and actual status codes
	        }
	        
	        @Override
	        protected void describeMismatchSafely(Integer actualStatusCode, Description mismatchDescription) {
	            mismatchDescription.appendText(String.format("but got: '%d'", actualStatusCode));
	        }
	    });
	}


	public static void assertHeader(Response response, String headerName, String expectedHeaderValue) {
	    String actualHeaderValue = response.getHeader(headerName);
	    
	    // Assert the header using a TypeSafeMatcher for detailed failure messages
	    assertThat(actualHeaderValue, new TypeSafeMatcher<String>() {
	        @Override
	        public void describeTo(Description description) {
	            description.appendText(String.format("Expected header '%s' to be: '%s'", 
	                                                  headerName, expectedHeaderValue));
	        }

	        @Override
	        protected boolean matchesSafely(String actualValue) {
	            return expectedHeaderValue.equals(actualValue); // Compare expected and actual header values
	        }
	        
	        @Override
	        protected void describeMismatchSafely(String actualValue, Description mismatchDescription) {
	            mismatchDescription.appendText(String.format("but got: '%s'", actualValue));
	        }
	    });
	}

	public static <T> void assertJsonPathValue(Response response, String jsonPath, T expectedValue) {
	    T actualValue = response.jsonPath().get(jsonPath);
	    
	    // Assert the JSON path value using a TypeSafeMatcher for detailed failure messages
	    assertThat(actualValue, new TypeSafeMatcher<T>() {
	        @Override
	        public void describeTo(Description description) {
	            description.appendText(String.format("Expected JSON path '%s' to return: '%s'", 
	                                                  jsonPath, expectedValue));
	        }

	        @Override
	        protected boolean matchesSafely(T item) {
	            return expectedValue.equals(item); // Compare expected and actual values
	        }
	        
	        @Override
	        protected void describeMismatchSafely(T item, Description mismatchDescription) {
	            mismatchDescription.appendText(String.format("but got: '%s'", item));
	        }
	    });
	}


	public static void assertJsonPathExists(Response response, String jsonPath) {
	    Object actualValue = response.jsonPath().get(jsonPath);
	    
	    // Assert that the JSON path exists using a TypeSafeMatcher
	    assertThat(actualValue, new TypeSafeMatcher<Object>() {
	        @Override
	        public void describeTo(Description description) {
	            description.appendText(String.format("Expected JSON path '%s' to exist", jsonPath));
	        }

	        @Override
	        protected boolean matchesSafely(Object item) {
	            return item != null; // Check if the item is not null
	        }
	        
	        @Override
	        protected void describeMismatchSafely(Object item, Description mismatchDescription) {
	            mismatchDescription.appendText(String.format("but it was null"));
	        }
	    });
	}


	public static void assertResponseNotEmpty(Response response) {
	    String responseBody = response.getBody().asString();

	    // Assert that the response body is not empty using a TypeSafeMatcher
	    assertThat(responseBody, new TypeSafeMatcher<String>() {
	        @Override
	        public void describeTo(Description description) {
	            description.appendText("Expected response body to be non-empty");
	        }

	        @Override
	        protected boolean matchesSafely(String item) {
	            return !isEmptyString().matches(item); // Check if the string is not empty
	        }

	        @Override
	        protected void describeMismatchSafely(String item, Description mismatchDescription) {
	            mismatchDescription.appendText("but it was empty");
	        }
	    });
	}


	public static void assertJsonPathIsEmpty(Response response, String jsonPath) {
	    Object actualValue = response.jsonPath().get(jsonPath);
	    
	    assertThat(actualValue, new TypeSafeMatcher<Object>() {
	        @Override
	        public void describeTo(Description description) {
	            description.appendText(String.format("Expected JSON path '%s' to be empty", jsonPath));
	        }

	        @Override
	        protected boolean matchesSafely(Object item) {
	            return is(emptyOrNullString()).matches(item);
	        }

	        @Override
	        protected void describeMismatchSafely(Object item, Description mismatchDescription) {
	            mismatchDescription.appendText(String.format("but got: '%s'", item));
	        }
	    });
	}


	public static void assertListSize(Response response, String jsonPath, int expectedSize) {
	    List<?> actualList = response.jsonPath().getList(jsonPath);
	    
	    assertThat(actualList, new TypeSafeMatcher<List<?>>() {
	        @Override
	        public void describeTo(Description description) {
	            description.appendText(String.format("Expected JSON path '%s' to have size: '%d'", jsonPath, expectedSize));
	        }

	        @Override
	        protected boolean matchesSafely(List<?> item) {
	            return (item != null ? item.size() : 0) == expectedSize;
	        }

	        @Override
	        protected void describeMismatchSafely(List<?> item, Description mismatchDescription) {
	            mismatchDescription.appendText(String.format("but got size: '%d'", item != null ? item.size() : "null"));
	        }
	    });
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
        assertThat(String.format("Expected status code '%d' but received '%d'. Reason: %s", 
                                 expectedStatusCode, actualStatusCode, reason), 
                   actualStatusCode, equalTo(expectedStatusCode));
    }
}
