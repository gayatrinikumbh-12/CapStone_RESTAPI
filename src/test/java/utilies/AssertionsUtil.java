package utilies;

import static org.testng.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
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

public class AssertionsUtil {

    public static <T> void assertResponseWithMatcher(Response response, String jsonPath, Matcher<T> matcher, String failureMessage) {
        T actualValue = response.jsonPath().get(jsonPath);
        assertThat(failureMessage, actualValue, matcher);
    }

    public static <T> void assertPropertyNotNull(Response response, String jsonPath, String errorMessage) {
        T actualValue = response.jsonPath().get(jsonPath);
        assertThat(actualValue, new TypeSafeMatcher<T>() {
            @Override
            public void describeTo(Description description) {
                description.appendText(String.format("%s: Expected JSON path '%s' to not be null.", errorMessage, jsonPath));
            }

            @Override
            protected boolean matchesSafely(T item) {
                return item != null;
            }

            @Override
            protected void describeMismatchSafely(T item, Description mismatchDescription) {
                mismatchDescription.appendText(String.format("was null (actual value: '%s')", item));
            }
        });
    }

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        assertThat(actualStatusCode, createEqualityMatcher(expectedStatusCode,
            String.format("Expected status code: '%d', but got: '%d'", expectedStatusCode, actualStatusCode)));
    }

    public static void assertStatusLine(Response response, String expectedStatusLine) {
        String actualStatusLine = response.getStatusLine();
        assertThat(actualStatusLine, createEqualityMatcher(expectedStatusLine,
            String.format("Expected status line: '%s', but got: '%s'", expectedStatusLine, actualStatusLine)));
    }

    public static void assertHeader(Response response, String headerName, String expectedHeaderValue) {
        String actualHeaderValue = response.getHeader(headerName);
        assertThat(actualHeaderValue, createEqualityMatcher(expectedHeaderValue,
            String.format("Expected header '%s' to be: '%s', but got: '%s'", headerName, expectedHeaderValue, actualHeaderValue)));
    }

    public static <T> void assertJsonPathValue(Response response, String jsonPath, T expectedValue) {
        T actualValue = response.jsonPath().get(jsonPath);
        assertThat(actualValue, createEqualityMatcher(expectedValue,
            String.format("Expected JSON path '%s' to return: '%s', but got: '%s'", jsonPath, expectedValue, actualValue)));
    }

    public static void assertJsonPathExists(Response response, String jsonPath) {
        Object actualValue = response.jsonPath().get(jsonPath);
        assertThat(actualValue, new TypeSafeMatcher<Object>() {
            @Override
            public void describeTo(Description description) {
                description.appendText(String.format("Expected JSON path '%s' to exist", jsonPath));
            }

            @Override
            protected boolean matchesSafely(Object item) {
                return item != null;
            }

            @Override
            protected void describeMismatchSafely(Object item, Description mismatchDescription) {
                mismatchDescription.appendText("but it was null");
            }
        });
    }

    public static void assertResponseNotEmpty(Response response) {
        String responseBody = response.getBody().asString();
        assertThat(responseBody, new TypeSafeMatcher<String>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Expected response body to be non-empty");
            }

            @Override
            protected boolean matchesSafely(String item) {
                return !item.isEmpty();
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
                return item == null || (item instanceof String && ((String) item).isEmpty());
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
        assertThat(actualStructure, createEqualityMatcher(expectedStructure,
            String.format("Expected JSON structure at path '%s' to match: '%s', but got: '%s'", jsonPath, expectedStructure, actualStructure)));
    }

    public static void assertResponseTimeLessThan(Response response, long maxResponseTime) {
        long actualResponseTime = response.getTime();
        assertThat(actualResponseTime, new TypeSafeMatcher<Long>() {
            @Override
            public void describeTo(Description description) {
                description.appendText(String.format("Expected response time to be less than '%d' ms", maxResponseTime));
            }

            @Override
            protected boolean matchesSafely(Long item) {
                return item < maxResponseTime;
            }

            @Override
            protected void describeMismatchSafely(Long item, Description mismatchDescription) {
                mismatchDescription.appendText(String.format("but got: '%d' ms", item));
            }
        });
    }

    public static void assertResponseFieldCondition(Response response, String jsonPath, String condition, String errorMessage) {
        Object actualValue = response.jsonPath().get(jsonPath);
        assertThat(actualValue, new TypeSafeMatcher<Object>() {
            @Override
            public void describeTo(Description description) {
                description.appendText(String.format("%s: Expected JSON path '%s' to have property '%s'", errorMessage, jsonPath, condition));
            }

            @Override
            protected boolean matchesSafely(Object item) {
                return item != null && Matchers.hasProperty(condition).matches(item);
            }

            @Override
            protected void describeMismatchSafely(Object item, Description mismatchDescription) {
                mismatchDescription.appendText(String.format("but it does not have property '%s'", condition));
            }
        });
    }

    public static void assertResponseField(Response response, String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        assertThat(actualValue, createEqualityMatcher(expectedValue,
            String.format("Expected JSON path '%s' to be '%s', but got: '%s'", jsonPath, expectedValue, actualValue)));
    }

    public static void assertStatusCodeWithReason(Response response, int expectedStatusCode, String reason) {
        int actualStatusCode = response.getStatusCode();
        assertThat(actualStatusCode, createEqualityMatcher(expectedStatusCode,
            String.format("Expected status code '%d'. Reason: %s, but received '%d'", expectedStatusCode, reason, actualStatusCode)));
    }

    private static <T> TypeSafeMatcher<T> createEqualityMatcher(T expectedValue, String message) {
        return new TypeSafeMatcher<T>() {
            @Override
            public void describeTo(Description description) {
                description.appendText(message);
            }

            @Override
            protected boolean matchesSafely(T actualValue) {
                return expectedValue.equals(actualValue);
            }

            @Override
            protected void describeMismatchSafely(T actualValue, Description mismatchDescription) {
                mismatchDescription.appendText(String.format("but got: '%s'", actualValue));
            }
        };
    }
}