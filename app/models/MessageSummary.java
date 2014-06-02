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
@Entity
@Table(name = "MESSAGE_SUMMARY")
public class MessageSummary {
	
	@Id
	@GeneratedValue
	@Column(name="MESSAGE_SUMMARY_ID")
	public int id;
	
	@Column(name="TOTAL_MESSAGES")
	public long totalMessages;
	
	@Column(name="UNREAD_MESSAGES")
	public long unreadMessages;
	
	@OneToOne
	@JoinColumn(name="PERSON_ID")
	public Person person;

	public MessageSummary(){

	}

	

}