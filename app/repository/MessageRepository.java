package repository;
import java.util.ArrayList;
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
	public Message createMessage(Message message){
		em.persist(message);
		em.flush();
		return message;
	}

	/**
	 * 
	 * @param message
	 */
	public Message updateMessage(Message message){
		em.merge(message);
		em.flush();
		return message;
	}

	/**
	 * 
	 * @param personID
	 */
	public MessageSummary getMessageSummary(Person person){
		List<MessageSummary> tmp = em.createNativeQuery(
			    "select * from MESSAGE_SUMMARY ms where ms.person_id = ?", MessageSummary.class)
			    .setParameter(1, person.id)
			    .setMaxResults(10)
			    .getResultList();
		if(tmp != null) {
			return (MessageSummary) tmp.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param personID
	 */
	public List<Message> findMessagesByTransmitter(Person person){
		List<Message> tmp = em.createNativeQuery(
			    "SELECT * FROM MESSAGE m where m.transmitter_id = ?", Message.class)
			    .setParameter(1, person.id)
			    .setMaxResults(10)
			    .getResultList();
		if (tmp != null) {
			return tmp;
		} else {
			return new ArrayList<Message>();
		}
	}

	/**
	 * 
	 * @param personID
	 */
	public List<Message> findMessagesByReceiver(Person person){
		List<Message> tmp = em.createNativeQuery(
			    "SELECT * FROM MESSAGE m where m.receiver_id = ?", Message.class)
			    .setParameter(1, person.id)
			    .setMaxResults(10)
			    .getResultList();
		if (tmp != null) {
			return tmp;
		} else {
			return new ArrayList<Message>();
		}
	}

}