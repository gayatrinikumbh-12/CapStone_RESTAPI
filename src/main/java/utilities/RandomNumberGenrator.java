package utilities;

import java.util.UUID;

import com.github.javafaker.Faker;

public class RandomNumberGenrator {

	/*
	public String RandomEmailGenrator()
	{
		return UUID.randomUUID().toString()+"@gmail.com";
	}
	*/
	

    private final Faker faker = new Faker();

    public String randomEmail() {
        return faker.internet().emailAddress();
    }
}
