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
	    ObjectMapper objectMapper = new ObjectMapper();
	    Object jsonResponse = response.getBody().jsonPath().get();

	    T responseObject = objectMapper.convertValue(jsonResponse, responseType);

	    if (responseObject instanceof UserSignupResponse) {
	        ((UserSignupResponse) responseObject).setStatusCode(response.getStatusCode());
	        ((UserSignupResponse) responseObject).setHeaders(response.getHeaders()); // Direct assignment of Headers
	    }

	    return responseObject;
	}
	// Private method to set a field's value in the responseObject if the field
	// exists.
	private static <T> void setFieldIfExists(Class<T> responseType, T responseObject, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
		  if (value != null) {
		    Field field = responseType.getDeclaredField(fieldName);
		    Class<?> fieldType = field.getType();
		    if (fieldType.isAssignableFrom(value.getClass())) {
		      // Only set the field if value's type matches or is a subclass of the field type
		      field.setAccessible(true);
		      field.set(responseObject, value);
		      field.setAccessible(false);
		    } else {
		      // Log a warning or throw a more specific exception if types don't match
		      System.out.println("Warning: Field type mismatch for " + fieldName + ". Expected: " + fieldType + ", Received: " + value.getClass());
		    }
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
