package repository;
import java.util.List;

import javax.persistence.EntityManager;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import models.Account;
import models.Message;
import models.MessageSummary;
import models.Person;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:19
 */
public class MessageRepository {

	EntityManager em;
	
	public MessageRepository(){
		em = JPA.em();
	}


	/**
	 * 
	 * @param message
	 */
	@Transactional
	public Message createMessage(Message message){
		em.persist(message);
		em.flush();
		return message;
	}

	/**
	 * 
	 * @param message
	 */
	@Transactional
	public Message updateMessage(Message message){
		em.merge(message);
		em.flush();
		return message;
	}

	/**
	 * 
	 * @param personID
	 */
	@Transactional
	public MessageSummary getMessageSummary(Person person){
		List tmp = em.createQuery(
			    "SELECT c FROM MESSAGE_SUMMARY c WHERE c.PERSON_ID LIKE :custID")
			    .setParameter("custID", person.id)
			    .setMaxResults(10)
			    .getResultList();
		return (MessageSummary) tmp.get(0);
	}

	/**
	 * 
	 * @param personID
	 */
	@Transactional
	public List<Message> findMessagesByTransmitter(Person person){
		return  em.createQuery(
			    "SELECT c FROM MESSAGE c WHERE c.TRANSMITTER_ID LIKE :custID")
			    .setParameter("custID", person.id)
			    .setMaxResults(10)
			    .getResultList();
	}

	/**
	 * 
	 * @param personID
	 */
	@Transactional
	public List <Message> findMessagesByReceiver(Person person){
		return  em.createQuery(
			    "SELECT c FROM MESSAGE c WHERE c.RECEIVER_ID LIKE :custID")
			    .setParameter("custID", person.id)
			    .setMaxResults(10)
			    .getResultList();
	}

}