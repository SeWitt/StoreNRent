package models;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
		
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "offer", orphanRemoval = true)
	public List<Picture> pictures;
	
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
	@Column(name="CREATED_DATE")
	public Timestamp createdDate;
	
	/**
	 * offer last edited
	 */
	@Column(name="LAST_EDITED_DATE")
	public Timestamp lastEditedDate;
	
	/**
	 * long offer description
	 */
	@Lob
	@Column(name="DESCRIPTION", length = 100000)
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
	@Column(name="CONTRACTED_FROM")
	public Timestamp contractedFrom;
	
	/**
	 * if somebody take the offer the real end of the crontract
	 */
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
	@Column(name="OFFER_FROM")
	public Timestamp offerFrom;
	
	/**
	 * last possible day of the contract
	 */
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
		
		
		if (of.pictures != null) {
			for (Picture pic : of.pictures) {
				addPicture(pic);
			}
		}
		
		id = of.id;
		city = of.city;
		street = of.street;
		postCode = of.postCode;
		houseNr = of.houseNr;
		country = of.country;
		spaceSize = of.spacesize;
		
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
			
		price = of.price;
		isActive = of.isActive;
		description = of.description;
		header = of.header;
		subHeader = of.subHeader;
		visitCount =of.visitCount;
		
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
	
	public void addPicture(Picture pic) {
		if (pictures == null) {
			pictures = new ArrayList<Picture>();
		}
		pic.offer = this;
		pictures.add(pic);
	}

	public void removePicture(Picture pic) {
		if (pictures != null) {
			pictures.remove(pic);
		}
	}

}
