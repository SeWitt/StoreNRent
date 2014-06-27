package models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Column(name="DATE_OF_BIRTH")
	public Timestamp dateOfBirth;
	
	@Column(name="IS_VERIFIED")
	public boolean isVerified;
	
	@Column(name="IS_ACTIVE")
	public boolean isActive;
	
	@Column(name="CREATED")
	public Timestamp created;
	
	@Column(name="LAST_EDITED")
	public Timestamp lastEdited;
	
//	@Lob
//	@Column(length=100000, name="PICTURE")
//	public byte[] picture;
	
	@Transient
	public String picturePath;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="person")
	public PersonSettings personSettings;
	

	
	public Person(){

	}

}
