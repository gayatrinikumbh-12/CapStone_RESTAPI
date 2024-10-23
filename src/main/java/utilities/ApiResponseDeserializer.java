package utilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;



public class ApiResponseDeserializer {

    public static <T> T deserializeResponse(Response response, Class<T> responseType) {
        ObjectMapper objectMapper = new ObjectMapper();
   
        Object jsonResponse = response.getBody().jsonPath().get();
        if (jsonResponse == null) {
            throw new IllegalArgumentException("Response body is null.");
        }

        T responseObject;
        if (jsonResponse instanceof Map) {
            responseObject = objectMapper.convertValue(jsonResponse, responseType);
        } else {
            throw new IllegalArgumentException("Unsupported JSON structure: Expected a JSON object, but found " + jsonResponse.getClass().getSimpleName());
        }

        setFieldIfExists(responseType, responseObject, "statusCode", response.getStatusCode());

        Map<String, String> headersMap = convertHeadersToMap(response.getHeaders());
        setFieldIfExists(responseType, responseObject, "headers", headersMap);

        return responseObject;
    }

    private static <T> void setFieldIfExists(Class<T> responseType, T responseObject, String fieldName, Object value) {
        Field field = null;
    	try {
             field = responseType.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(responseObject, value);
        } catch (NoSuchFieldException e) {
            // Handle field not found gracefully
            System.err.println("Field '" + fieldName + "' not found in response type " + responseType.getName());
        } catch (IllegalAccessException e) {
            // Handle field access issues gracefully
            System.err.println("Error accessing field '" + fieldName + "' in response type " + responseType.getName());
        } finally {
            field.setAccessible(false);
        }
    }

    private static Map<String, String> convertHeadersToMap(Headers headers) {
        if (headers == null) {
            return Collections.emptyMap(); // Return an empty map if headers are null
        }

        Map<String, String> headersMap = new HashMap<>();
        for (Header header : headers) {
            headersMap.put(header.getName(), header.getValue());
        }
        return headersMap;
    }
}