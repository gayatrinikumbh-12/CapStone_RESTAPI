package models;

import static org.testng.Assert.assertNotNull;

import lombok.Builder;
import lombok.Data;

@Data

public class CartRequest {

	private String cart_id;

	private String user_id;

	private String created_at;
	
	private int StatusCode;
	

	public void assertCartCreatedSuccessfully() {
		
		assertNotNull(this.getCart_id(), "Cart ID should not be null");
        assertNotNull(this.getUser_id(), "User ID must be present");
        assertNotNull(this.getCreated_at(), "created at string  be present");
       int Code = this.getStatusCode();
       System.out.println("cart status code ---- "+Code);
        //assertThat("Expected status code to be 201 for successful user creation. Actual status code: " + this.getStatusCode(), 
              //  this.getStatusCode(), equalTo(201));
    }


}
