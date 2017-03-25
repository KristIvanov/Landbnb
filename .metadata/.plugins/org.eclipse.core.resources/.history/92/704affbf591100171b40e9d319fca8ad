package places;

public class Address {

	public enum Region {
		BLAGOEVGRAD, BURGAS, VARNA, VELIKOTARNOVO,
		VIDIN, VRATSA, GABROVO, DOBRICH, KARDDZALI,
		KYUSTENDIL, LOVECH, MONTANA, PAZARDZIK,
		PERNIK, PLEVEN, PLOVDIV, RAZGRAD, RUSE,
		SILISTRA, SLIVEN, SMOLYAN, SOFIA, STARAZAGORA,
		TARGOVISHTE, HASKOVO, SHUMEN, YAMBOL
		}
		
	private Region region;
	private String city;
	private String street;
	private int number;
	
	@Override
	public String toString() {
		return (this.street + " " + this.number + ", " + this.city + ", " + this.region.toString());
	}

	public Address(Region region, String city, String street, int number) {
		
		this.region = region;
		if (city!=null && !city.isEmpty()) this.city = city;
		if (street!=null && !street.isEmpty()) this.street = street;
		if (number > 0) this.number = number;
	}
	
	public Region getRegion() {
		return region;
	}
	
	
}
