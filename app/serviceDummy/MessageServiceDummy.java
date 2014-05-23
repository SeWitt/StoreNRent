package serviceDummy;

import java.util.List;

import model.Message;
import model.MessageSummary;
import service.MessageService;

public class MessageServiceDummy implements MessageService {

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
	public MessageSummary getMessageSummary(long personID) {
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
