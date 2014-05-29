package models;

import java.sql.Blob;
import java.util.Date;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:23
 */
public class Person {

	public int id;
	public String surname;
	public String lastName;
	public String street;
	public String houseNr;
	public String city;
	public String postCode;
	public String country;
	public Date dateOfBirth;
	public boolean isVerified;
	public boolean isActive;
	public Date created;
	public Date lastEdited;
	public Blob picture;
	public PersonSettings personSettings;

	public Person(){

	}

}