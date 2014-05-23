package repository;
import java.util.List;

import model.Message;
import model.MessageSummary;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:19
 */
public class MessageRepository {

	public MessageRepository(){

	}

	public void finalize() throws Throwable {

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
	public MessageSummary getMessageSummary(long personID){
		return null;
	}

	/**
	 * 
	 * @param personID
	 */
	public List<Message> findMessagesByTransmitter(long personID){
		return null;
	}

	/**
	 * 
	 * @param personID
	 */
	public List <Message> findMessagesByReceiver(long personID){
		return null;
	}

}