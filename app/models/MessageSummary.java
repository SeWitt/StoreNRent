package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:20
 */

public class MessageSummary {
	
	
	public int id;
	
	
	public long totalMessages;
	
	
	public long unreadMessages;
	
	
	public Person person;

	public MessageSummary(){

	}

	

}