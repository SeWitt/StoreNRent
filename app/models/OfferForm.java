package models;

import java.sql.Blob;
import java.text.SimpleDateFormat;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import validators.IsDateNotPaste;

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
		geolocX = o.geolocX + "";
		geolocY = o.geolocY + "";
		picturePath1 = o.picturePath1;
		picturePath2 = o.picturePath2;
		picturePath3 = o.picturePath3;
		picturePath4 = o.picturePath4;
		picturePath5 = o.picturePath5;
		price = o.price;
		isActive = o.isActive;
		description = o.description;
		header = o.header;
		subHeader = o.subHeader;
		visitCount =o.visitCount;
		if(o.contractedFrom != null){
			contractedFrom = new SimpleDateFormat(GlobalValues.DATEFORMAT).format( o.contractedFrom);
		}
		if(o.contractedUntil != null){
					contractedUntil = new SimpleDateFormat(GlobalValues.DATEFORMAT).format( o.contractedUntil);
		}
		if(o.offerFrom != null){
			offerFrom = new SimpleDateFormat(GlobalValues.DATEFORMAT).format( o.offerFrom);
		}
		if(o.offerTo != null){
			offerTo = new SimpleDateFormat(GlobalValues.DATEFORMAT).format( o.offerTo);
		}
	
		transactionClosed = o.transactionClosed;
		owner = o.owner;
		acceptor = o.acceptor;
	}

	public int id;
	
	@MinLength(value = 4 , message ="The city name must be longer than 4 characters!")
	@Required(message = "Please insert a city name!")
	public String city;
	
	@MinLength(value = 4 , message ="The street name must be longer than 4 characters!")
	@Required(message = "Please insert a street name!" )
	public String street;
	
	@MinLength(value = 4 , message ="The postcode name must be longer than 4 characters!")
	@Required(message = "Please insert a postcode!" )
	public String postCode;
	
	
	@Required(message = "Please insert a house number" )
	public String houseNr;
	
	@MinLength(value = 4 , message ="The country name must be longer than 4 characters!")
	@Required(message = "Please insert a country!" )
	public String country;
	
	public String geolocX;
	
	public String geolocY;
	
	public String picturePath1;
	
	public String picturePath2;
	
	public String picturePath3;
	
	public String picturePath4;
	
	public String picturePath5;
	
	@Required(message = "Please insert a valid price!")
	public double price;
	
	/**
	 * true = is displayed ; false = "deleted"
	 */
	public boolean isActive;
			
	/**
	 * long offer description
	 */
	@MinLength(value = 4 , message ="The description name must be longer than 4 characters!")
	@Required(message = "Please insert a description!" )
	public String description;
	
	/**
	 * header line
	 */
	@Required(message = "Please insert a header!" )
	@MinLength(value = 4 , message ="The header must be longer than 4 characters!")
	@MaxLength(value = 50 , message ="The header is too long!")
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
	@IsDateNotPaste()
	@Required(message = "Please insert a vaild start date!" )
	public String offerFrom;
	
	/**
	 * last possible day of the contract
	 */
	@Required(message = "Please insert a vaild end date!" )
	public String offerTo;
	
	public Person owner;

	public Person acceptor;
	
}