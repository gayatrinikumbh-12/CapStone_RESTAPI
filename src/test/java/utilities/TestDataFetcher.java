package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDataFetcher {

	
	 private static final String TEST_DATA_JSON_PATH = "src/test/resources/testdata.json";
	    private static JsonNode testData;

	    static {
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            testData = objectMapper.readTree(new File(TEST_DATA_JSON_PATH));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static JsonNode getTestDataNode(String key) {
	        return testData.get(key);
	    }
	
	
	
}
