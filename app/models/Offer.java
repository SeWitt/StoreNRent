package models;

import java.sql.Clob;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import appinfo.GlobalValues;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:22
 */
@Entity
@Table(name = "OFFER")
public class Offer {
	
	@Id
	@GeneratedValue
	@Column(name="OFFER_ID")
	public int id;
	
	@Column(name="CITY")
	public String city;
	
	@Column(name="STREET")
	public String street;
	
	@Column(name="POSTCODE")
	public String postCode;
	
	@Column(name="HOUSE_NR")
	public String houseNr;
	
	@Column(name="COUNTRY")
	public String country;
	
	@Column(name="GEOLOCX")
	public double lng;
	
	@Column(name="GEOLOCY")
	public double lat;
	
//	@Lob
//	@Column(length=100000, name="PICTURE")
//	public byte[] picture;
//	@Column(name="PICTURE")
//	public Blob picture;
	
	@Column(name="PICTURE_PATH_1")
	public Clob picturePath1;
	
	@Column(name="PICTURE_PATH_2")
	public Clob picturePath2;
	
	@Column(name="PICTURE_PATH_3")
	public Clob picturePath3;
	
	@Column(name="PICTURE_PATH_4")
	public Clob picturePath4;
	
	@Column(name="PICTURE_PATH_5")
	public Clob picturePath5;
	
	@Column(name="PRICE")
	public double price;
	
	/**
	 * in square meter
	 */
	@Column(name="SPACE_SIZE")
	public double spaceSize;
	
	/**
	 * true = is displayed ; false = "deleted"
	 */
	@Column(name="IS_ACTIVE")
	public boolean isActive;
	
	/**
	 * offer creation date
	 */
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	public Timestamp createdDate;
	
	/**
	 * offer last edited
	 */
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_EDITED_DATE")
	public Timestamp lastEditedDate;
	
	/**
	 * long offer description
	 */
	@Column(name="DESCRIPTION")
	public String description;
	
	/**
	 * header line
	 */
	@Column(name="HEADER")
	public String header;
	
	/**
	 * subheader line
	 */
	@Column(name="SUB_HEADER")
	public String subHeader;
	
	@Column(name="VISIT_COUNT")
	public long visitCount;
	
	/**
	 * if somebody take the offer the real begin of the crontract
	 */
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CONTRACTED_FROM")
	public Timestamp contractedFrom;
	
	/**
	 * if somebody take the offer the real end of the crontract
	 */
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CONTRACTED_UNTIL")
	public Timestamp contractedUntil;
	
	/**
	 * if true, the transaction was finished 
	 */
	@Column(name="TRANSACTION_CLOSED")
	public boolean transactionClosed;
	
	/**
	 * first possible day of the contract
	 */
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OFFER_FROM")
	public Timestamp offerFrom;
	
	/**
	 * last possible day of the contract
	 */
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OFFER_TO")
	public Timestamp offerTo;
	
	/**
	 * distance to the location specified in the search query (unit is kilometers)
	 */
	@Transient
	public double distance;
	
	@ManyToOne
	@JoinColumn(name="OWNER_ID")
	public Person owner;
	
	@ManyToOne
	@JoinColumn(name="ACCEPTOR_ID")
	public Person acceptor;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="intendedOffer")
	public Recommendation recommendation;
	
	public Offer(){

	}

	public Offer(OfferForm of) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalValues.DATEFORMAT);
		
		
		
		id = of.id;
		city = of.city;
		street = of.street;
		postCode = of.postCode;
		houseNr = of.houseNr;
		country = of.country;
		
		if(of.geolocX != null){
				try{
				lng = (new Double(of.geolocX)).doubleValue();
			} catch(Exception e) {
				e.printStackTrace();
				//TODO
			}
		}
		
		if(of.geolocY != null){
			try{
				lat = (new Double(of.geolocY)).doubleValue();
			} catch(Exception e) {
				e.printStackTrace();
				//TODO
			}
		}
			
//		picture = of.picture;
		price = of.price;
		isActive = of.isActive;
		description = of.description;
		header = of.header;
		subHeader = of.subHeader;
		visitCount =of.visitCount;
		
		
//		contractedFrom =    new Timestamp()        new SimpleDateFormat(GlobalValues.TIMEFORMAT).format( o.contractedFrom);
//		contractedUntil = new SimpleDateFormat(GlobalValues.TIMEFORMAT).format( o.contractedUntil);
		transactionClosed = of.transactionClosed;
		
		try {
			offerFrom = new Timestamp((dateFormat.parse(of.offerFrom)).getTime());
			offerTo = new Timestamp((dateFormat.parse(of.offerTo)).getTime());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		owner = of.owner;
		acceptor = of.acceptor;
	}



}
