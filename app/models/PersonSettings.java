package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:25
 */
@Entity
@Table(name ="PERSON_SETTINGS")
public class PersonSettings {

	@Id
	@GeneratedValue
	@Column(name="PERSON_SETTINGS_ID")
	public long id;
	
	@Column(name="SEND_NEWSLETTER")
	public boolean sendNewsletter;
	
	@Column(name="DISPLEY_FIRST_NAME_ONLY")
	public boolean displayFirstNameOnly;
	
	@OneToOne
	@JoinColumn(name="PERSON_ID")
	public Person person;
	
	@Column(name="IS_ACTIVE")
	public boolean isActive;

	public PersonSettings(){

	}

	
}