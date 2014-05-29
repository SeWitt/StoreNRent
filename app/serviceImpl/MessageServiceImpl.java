package serviceImpl;
import java.util.Date;
import java.util.List;

import service.MessageService;
import repository.MessageRepository;
import models.Message;
import models.MessageSummary;
import models.Person;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:20
 */
public class MessageServiceImpl implements MessageService {

	private MessageRepository messageRepo = new MessageRepository();

	public MessageServiceImpl(){

	}

	@Override
	public Message createMessage(Message message) {
		
		message.createdDate = new Date();
		message.isRead = false;
		message.isActive = true;
		//TODO: Basic filtering -> exclude mailadresses and telephone numbers
		
		return messageRepo.createMessage(message);
	}

	@Override
	public Message updateMessage(Message message) {
		
		if(message.isActive = true){
			//TODO: Basic filtering -> exclude mailadresses and telephone numbers
			message = messageRepo.updateMessage(message);
		}
		
		return message;
	}

	@Override
	public MessageSummary getMessageSummary(Person person) {
		
		return messageRepo.getMessageSummary(person);
	}

	@Override
	public List<Message> findMessageByTransmitter(Person person) {
		
		return messageRepo.findMessagesByTransmitter(person);
	}

	@Override
	public List<Message> findMessageByReceiver(Person person) {
		
		return messageRepo.findMessagesByReceiver(person);
	}

	@Override
	public void deleteMessage(Message message) {
		message.isActive = false;
		
		messageRepo.updateMessage(message);
		
	}

	

	

}