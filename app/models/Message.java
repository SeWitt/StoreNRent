package models;

import java.util.Date;

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
	public Person transmitter;
	public Person receiver;
	public boolean isActive;

	public Message(){

	}


}