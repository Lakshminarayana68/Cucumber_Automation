package utilities;

import com.github.javafaker.Country;
import com.github.javafaker.Faker;

public class DataGenerator {
	
	private static Faker faker=new Faker();
	
	public static String createUsername() {
		return faker.name().username();
	}
	
	public static String generateRandomEmail() {
		return faker.internet().emailAddress();
	}
	
	public static String generatePhoneNumber() {
		return faker.phoneNumber().cellPhone();
	}
	
	public static String generatePassword() {
		return faker.internet().password();
	}
	
	public static String generateCity() {
		return faker.address().cityName();
	}
	public static String generateCountry() {
		return faker.country().countryCode2();
	}
	public static String generateZipCode() {
		return faker.address().zipCode();
	}
	
	public static String generateAddress() {
		return faker.address().fullAddress();
	}
	public static String generateState() {
		return faker.address().state();
	}

}
