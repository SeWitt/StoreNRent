package service;

import java.util.List;

import model.Message;
import model.MessageSummary;

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
	public abstract MessageSummary getMessageSummary(long personID);

	/**
	 * 
	 * @param personID
	 */
	public abstract List<Message> findMessageByTransmitter(long personID);

	/**
	 * 
	 * @param personID
	 */
	public abstract List<Message> findMessageByReceiver(long personID);

	/**
	 * 
	 * @param message
	 */
	public abstract void deleteMessage(Message message);

}