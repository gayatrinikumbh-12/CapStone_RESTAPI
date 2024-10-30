package models;



import lombok.Builder;
import lombok.Data;

@Data

public class CartRequest {
		
	private String cart_id;
	
    private String user_id;
    
    private String created_at;
	 
}

