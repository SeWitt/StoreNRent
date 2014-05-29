package service;

import java.util.List;

import models.Message;
import models.MessageSummary;
import models.Person;

public interface MessageService {

	/**
	 * 
	 * @param message
	 */
	public abstract Message createMessage(Message message);

	/**
	 * 
	 * @param message
	 */
	public abstract Message updateMessage(Message message);

	/**
	 * 
	 * @param personID
	 */
	public abstract MessageSummary getMessageSummary(Person person);

	/**
	 * 
	 * @param personID
	 */
	public abstract List<Message> findMessageByTransmitter(Person person);

	/**
	 * 
	 * @param personID
	 */
	public abstract List<Message> findMessageByReceiver(Person person);

	/**
	 * 
	 * @param message
	 */
	public abstract void deleteMessage(Message message);

}