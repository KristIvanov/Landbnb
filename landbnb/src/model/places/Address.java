package model.places;

public class Address {

	public enum Region {
		BLAGOEVGRAD, BURGAS, VARNA, VELIKOTARNOVO,
		VIDIN, VRATSA, GABROVO, DOBRICH, KARDDZALI,
		KYUSTENDIL, LOVECH, MONTANA, PAZARDZIK,
		PERNIK, PLEVEN, PLOVDIV, RAZGRAD, RUSE,
		SILISTRA, SLIVEN, SMOLYAN, SOFIA, STARAZAGORA,
		TARGOVISHTE, HASKOVO, SHUMEN, YAMBOL
		}
		
	private String region;
	private String city;
	private String street;
	private String number;
	private int apartment;
	private long id;
	
	@Override
	public String toString() {
		return (this.street + " " + this.number + ", " + this.city + ", " + this.region.toString());
	}

	public Address(String region, String city, String street, String number, int ap) {
		
		this.region = region;
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
