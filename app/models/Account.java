package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:12
 */
@Entity
@Table(name = "ACCOUNT")
public class Account {
	
	@Id
	@GeneratedValue
	@Column(name="ACCOUNT_ID")
	public int id;
	
	@Column(name="EMAIL")
	public String email;
	
	@Column(name="PASSWORD")
	public String password;

	@Column(name="IS_ACTIVE")
	public boolean isActive;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	public Date createdDate;
	
	@OneToOne
	@JoinColumn(name="PERSON_ID")
	public Person person;

	public Account(){

	}

	public void finalize() throws Throwable {

	}

}