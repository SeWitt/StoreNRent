package models;

import java.sql.Blob;
import java.text.SimpleDateFormat;

import appinfo.GlobalValues;

public class OfferForm{

	public OfferForm() {}
	
	public OfferForm(Offer o) {
		id = o.id;
		city = o.city;
		street = o.street;
		postCode = o.postCode;
		houseNr = o.houseNr;
		country = o.country;
		geolocX = o.geolocX;
		geolocY = o.geolocY;
		picture = o.picture;
		price = o.price;
		isActive = o.isActive;
		description = o.description;
		header = o.header;
		subHeader = o.subHeader;
		visitCount =o.visitCount;
		if(o.contractedFrom != null){
			contractedFrom = new SimpleDateFormat(GlobalValues.TIMEFORMAT).format( o.contractedFrom);
		}
		if(o.contractedUntil != null){
					contractedUntil = new SimpleDateFormat(GlobalValues.TIMEFORMAT).format( o.contractedUntil);
		}
		if(o.offerFrom != null){
			offerFrom = new SimpleDateFormat(GlobalValues.TIMEFORMAT).format( o.offerFrom);
		}
		if(o.offerTo != null){
			offerTo = new SimpleDateFormat(GlobalValues.TIMEFORMAT).format( o.offerTo);
		}
	
		transactionClosed = o.transactionClosed;
		owner = o.owner;
		acceptor = o.acceptor;
	}

	public long id;
	
	public String city;
	
	public String street;

	public String postCode;
	
	public String houseNr;
	
	public String country;
	
	public String geolocX;
	
	public String geolocY;
	public Blob picture;
	
	public double price;
	
	/**
	 * true = is displayed ; false = "deleted"
	 */
	public boolean isActive;
			
	/**
	 * long offer description
	 */
	public String description;
	
	/**
	 * header line
	 */
	public String header;
	
	/**
	 * subheader line
	 */
	public String subHeader;
	
	public long visitCount;
	
	/**
	 * if somebody take the offer the real begin of the crontract
	 */
	public String contractedFrom;
	
	/**
	 * if somebody take the offer the real end of the crontract
	 */
	public String contractedUntil;
	
	/**
	 * if true, the transaction was finished 
	 */
	public boolean transactionClosed;
	
	/**
	 * first possible day of the contract
	 */
	public String offerFrom;
	
	/**
	 * last possible day of the contract
	 */
	public String offerTo;
	
	public Person owner;

	public Person acceptor;
	
}