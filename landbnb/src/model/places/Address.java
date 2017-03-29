package model.places;

import java.util.ArrayList;
import java.util.Arrays;

public class Address {
	
	public static String[] regions =  {"Blagoevgrad", "Burgas", "Varna", "Veliko Tarnovo",
			"Vidin", "Vratsa", "Gabrovo", "Dobrich", "Kardzali", "Kyustendil", "Lovech",
			"Montana", "Pazardzik", "Pernik", "Pleven", "Plovdiv", "Razgrad", "Ruse",
			"Silistra", "Sliven", "Smolyan", "Sofia", "Stara Zagora" , "Targovishte",
			"Haskovo", "Shumen", "Yambol"};
		
	private String region;
	private String city;
	private String street;
	private String number;
	private int apartment;
	private long id;
	
	@Override
	public String toString() {
		return (this.street + " " + this.number + ", " + this.city + ", " + this.region);
	}

	public Address(String region, String city, String street, String number, int ap) {
		
		if (region!=null && !region.isEmpty() && Arrays.asList(regions).contains(region)) this.region = region;
		if (city!=null && !city.isEmpty()) this.city = city;
		if (street!=null && !street.isEmpty()) this.street = street;
		if (number !=null && !number.isEmpty()) this.number = number;
		if (ap > 0) this.apartment = ap;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public String getCity() {
		return city;
	}
	
	public int getApartment() {
		return apartment;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getNumber() {
		return number;
	}
	
	
}
