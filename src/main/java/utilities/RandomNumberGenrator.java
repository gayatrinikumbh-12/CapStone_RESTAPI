package utilities;

import java.util.UUID;

public class RandomNumberGenrator {

	
	public String RandomEmailGenrator()
	{
		return UUID.randomUUID().toString()+"@gmail.com";
	}
}
