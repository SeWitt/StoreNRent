package repository;
import java.util.List;

import model.Message;
import model.MessageSummary;
import model.Person;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:19
 */
public class MessageRepository {

	public MessageRepository(){

	}


	/**
	 * 
	 * @param message
	 */
	public Message createMessage(Message message){
		return null;
	}

	/**
	 * 
	 * @param message
	 */
	public Message updateMessage(Message message){
		return null;
	}

	/**
	 * 
	 * @param personID
	 */
	public MessageSummary getMessageSummary(Person person){
		return null;
	}

	/**
	 * 
	 * @param personID
	 */
	public List<Message> findMessagesByTransmitter(Person person){
		return null;
	}

	/**
	 * 
	 * @param personID
	 */
	public List <Message> findMessagesByReceiver(Person person){
		return null;
	}

}