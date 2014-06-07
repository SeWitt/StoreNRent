package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:18
 */
@Entity
@Table(name = "MESSAGE")
public class Message {

	@Id
	@GeneratedValue
	@Column(name="MESSAGE_ID")
	public long id;
	
	@Column(name="TEXT")
	public String text;
	
	@Column(name="CREATED_DATE")
	public Date createdDate;
	
	@Column(name="IS_READ")
	public boolean isRead;
	
	@Column(name="SUBJECT")
	public String subject;
	
	@ManyToOne
	@JoinColumn(name="TRANSMITTER_ID")
	public Person transmitter;
	
	@ManyToOne
	@JoinColumn(name="RECEIVER_ID")
	public Person receiver;
	
	@Column(name="IS_ACTIVE")
	public boolean isActive;

	public Message(){

	}


}