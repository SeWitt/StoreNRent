package serviceImpl;
import java.util.List;

import model.Message;
import service.MessageService;
import repository.MessageRepository;
import model.MessageSummary;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message updateMessage(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public model.MessageSummary getMessageSummary(long personID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findMessageByTransmitter(long personID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findMessageByReceiver(long personID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

	

}