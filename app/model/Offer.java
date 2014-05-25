package model;

import java.util.Date;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:22
 */
public class Offer {

	public long id;
	public String city;
	public String street;
	public String houseNr;
	public String country;
	public String geolocX;
	public String geolocY;
	/**
	 * true = is displayed ; false = "deleted"
	 */
	public boolean isActive;
	/**
	 * offer creation date
	 */
	public Date createdDate;
	/**
	 * offer last edited
	 */
	public Date lastEditedDate;
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
	public Date contractedFrom;
	/**
	 * if somebody take the offer the real end of the crontract
	 */
	public Date contractedUntil;
	/**
	 * if true, the transaction was finished 
	 */
	public boolean transactionClosed;
	/**
	 * first possible day of the contract
	 */
	public Date offerFrom;
	/**
	 * last possible day of the contract
	 */
	public Date offerTo;
	public Person owner;
	public Person acceptor;
	
	public Offer(){

	}



}