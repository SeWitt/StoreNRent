package models;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	public long id;
	
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
	public String geolocX;
	
	@Column(name="GEOLOCY")
	public String geolocY;
	public Blob picture;
	
	@Column(name="PRICE")
	public double price;
	
	/**
	 * true = is displayed ; false = "deleted"
	 */
	@Column(name="IS_ACTIVE")
	public boolean isActive;
	
	/**
	 * offer creation date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	public Date createdDate;
	
	/**
	 * offer last edited
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_EDITED_DATE")
	public Date lastEditedDate;
	
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CONTRACTED_FROM")
	public Date contractedFrom;
	
	/**
	 * if somebody take the offer the real end of the crontract
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CONTRACTED_UNTIL")
	public Date contractedUntil;
	
	/**
	 * if true, the transaction was finished 
	 */
	@Column(name="TRANSACTION_CLOSED")
	public boolean transactionClosed;
	
	/**
	 * first possible day of the contract
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OFFER_FROM")
	public Date offerFrom;
	
	/**
	 * last possible day of the contract
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OFFER_TO")
	public Date offerTo;
	
	
	@ManyToOne
	@JoinColumn(name="PERSON_ID")
	public Person owner;
	
	@ManyToOne
	@JoinColumn(name="PERSON_ID")
	public Person acceptor;
	
	//tbd: test if the following lines are required
	@OneToOne(cascade = CascadeType.ALL, mappedBy="intendedOffer")
	public Recommendation recommendation;
	
	public Offer(){

	}



}