package utilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import models.UserSignupResponse;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ApiResponseDeserializer {

	// Generic method to deserialize the response body to a specified type
	// (responseType).
	public static <T> T deserializeResponse(Response response, Class<T> responseType) {
		// ObjectMapper is used to map JSON to Java objects.
		ObjectMapper objectMapper = new ObjectMapper();
		// Extract the JSON response body.
		Object jsonResponse = response.getBody().jsonPath().get();

		T responseObject;
		
	
	    responseObject = objectMapper.convertValue(response.getBody().jsonPath().get(), responseType);
	    ((UserSignupResponse) responseObject).setStatusCode(response.getStatusCode());
	    ((UserSignupResponse) responseObject).setHeaders(new Headers((List<Header>) response.getHeaders())); // Change Map to Headers type
	    return responseObject;
	}

	// Private method to set a field's value in the responseObject if the field
	// exists.
	private static <T> void setFieldIfExists(Class<T> responseType, T responseObject, String fieldName, Object value) {
		try {
			// Get the declared field by name.
			System.out.println("responseType  " + responseType);
			System.out.println("responseObject  " + responseType);
			System.out.println("fieldName" + fieldName);
			Field field = responseType.getDeclaredField(fieldName);

			// Make the field accessible (in case it is private or protected).
			field.setAccessible(true);
			// Set the value of the field in the responseObject.
			field.set(responseObject, value);
			// Reset the field's accessibility to its original state.
			field.setAccessible(false);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			// Print stack trace if there's an error accessing the field.
			throw new RuntimeException("Failed to set value for the field: " + fieldName, e);
		}
	}

	// Converts Headers object from io.restassured.http.Headers to a Map<String,
	// String>.
	private static Map<String, String> convertHeadersToMap(Headers headers) {
		Map<String, String> headersMap = new HashMap<>();
		// Iterate through each Header and put its name and value in the headersMap.
		for (Header header : headers) {
			headersMap.put(header.getName(), header.getValue());
		}
		return headersMap;
	}

}
