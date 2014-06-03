package serviceDummy;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import models.Message;
import models.MessageSummary;
import models.Person;
import models.PersonSettings;
import service.MessageService;

public class MessageServiceDummy implements MessageService {

	@Override
	public Message createMessage(Message message) {
		
		message.createdDate = new Date();
		message.isRead = false;
		message.isActive = true;
		message.id = 5;
		
		return message;
	}

	@Override
	public Message updateMessage(Message message) {
		
	
		
		return message;
	}

	@Override
	public MessageSummary getMessageSummary(Person person) {
		
		MessageSummary msgS = new MessageSummary();
		msgS.person = person;
		msgS.totalMessages = 1337;
		msgS.unreadMessages = 4;
		
		
		return msgS;
	}

	@Override
	public List<Message> findMessageByTransmitter(Person person) {
		 List<Message> msg = new LinkedList<Message>();
		 
		 for(int i = 1; i < 9; i++){
			 Message m = new Message();
			 m.createdDate = new Date();
			 m.id = i;
			 m.isActive = true;
			 m.isRead = false;
			 m.transmitter = person;
			 
			 Person p = new Person();
				p.city = "Muenchen";
				p.country = "Deutschland";
				p.created = new Date();
				p.dateOfBirth = new Date();
				p.houseNr = "42";
				p.id = 6+i;
				p.isActive = true;
				p.isVerified = true;
				p.lastEdited = p.created;
				p.lastName = "Dummy";
				p.postCode = "12345";
				p.street = "Boltzmannstr";
				p.surname = "Peter";
				p.personSettings = new PersonSettings();
				p.personSettings.displayFirstNameOnly = false;
				p.personSettings.id = 3+i;
				p.personSettings.isActive = true;
				p.personSettings.person = p;
				p.personSettings.sendNewsletter = true;
				m.receiver = p;
				
				msg.add(m);
			 
		 }
		return msg;
	}

	@Override
	public List<Message> findMessageByReceiver(Person person) {
		
 List<Message> msg = new LinkedList<Message>();
		 
		 for(int i = 1; i < 9; i++){
			 Message m = new Message();
			 m.createdDate = new Date();
			 m.id = i;
			 m.isActive = true;
			 m.isRead = false;
			 m.receiver = person;
			 
			 Person p = new Person();
				p.city = "Muenchen";
				p.country = "Deutschland";
				p.created = new Date();
				p.dateOfBirth = new Date();
				p.houseNr = "42";
				p.id = 6+i;
				p.isActive = true;
				p.isVerified = true;
				p.lastEdited = p.created;
				p.lastName = "Dummy";
				p.postCode = "12345";
				p.street = "Boltzmannstr";
				p.surname = "Peter";
				p.personSettings = new PersonSettings();
				p.personSettings.displayFirstNameOnly = false;
				p.personSettings.id = 3+i;
				p.personSettings.isActive = true;
				p.personSettings.person = p;
				p.personSettings.sendNewsletter = true;
				m.transmitter = p;
				
				msg.add(m);
			 
		 }
		return msg;
	}

	@Override
	public void deleteMessage(Message message) {
		message.isActive = false;
		
	
		
	}

}
