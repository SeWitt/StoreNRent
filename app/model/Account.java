package model;

import java.sql.Date;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:12
 */
public class Account {

	public String email;
	public String password;
	public boolean isActive;
	public Date createdDate;
	public Person id;

	public Account(){

	}

	public void finalize() throws Throwable {

	}

}