package model;

import java.sql.Date;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:18
 */
public class Message {

	public long id;
	public String text;
	public Date createdDate;
	public boolean isRead;
	public String subject;
	public Person m_Person;

	public Message(){

	}

	public void finalize() throws Throwable {

	}

}