package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:23
 */
@Entity
@Table(name = "PERSON")
public class Person {

	@Id
	@GeneratedValue
	@Column(name="PERSON_ID")
	public int id;
	
	@Column(name="SURNAME")
	public String surname;
	
	@Column(name="LASTNAME")
	public String lastName;
	
	@Column(name="STREET")
	public String street;
	
	@Column(name="HOUSE_NR")
	public String houseNr;
	
	@Column(name="CITY")
	public String city;
	
	@Column(name="POST_CODE")
	public String postCode;
	
	@Column(name="COUNTRY")
	public String country;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_OF_BIRTH")
	public Date dateOfBirth;
	
	@Column(name="IS_VERIFIED")
	public boolean isVerified;
	
	@Column(name="IS_ACTIVE")
	public boolean isActive;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED")
	public Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_EDITED")
	public Date lastEdited;
	
	@Lob
	@Column(length=100000, name="PICTURE")
	public byte[] picture;
	
	
	// tbd: test if the following lines are required!
	@OneToOne(cascade = CascadeType.ALL, mappedBy="person")
	public PersonSettings personSettings;

	@OneToOne(cascade = CascadeType.ALL, mappedBy="person")
	public Account account;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="transmitter")
	public List<Message> messagesSent;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="receiver")
	public List<Message> messagesReceived;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="owner")
	public List<Offer> offersPosted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="acceptor")
	public List<Offer> offersAccepted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="transmitter")
	public List<Recommendation> recommendationsTransmitted;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="receiver")
	public List<Recommendation> recommendationsReceived;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="person")
	public RecommendationSummary recommendationSummary;
	
	public Person(){

	}

}