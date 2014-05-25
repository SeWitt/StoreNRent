package serviceDummy;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.Offer;
import model.Person;
import model.PersonSettings;
import repository.OfferRepository;
import service.OfferService;
import service.PersonalSettingsService;

public class OfferServiceDummy implements OfferService {

	private PersonalSettingsService personalSettingsService = new PersonalSettingsServiceDummy(); //if the backend is ready switch to "..Impl" instead of "..Dummy"


	public OfferServiceDummy(){

	}

	@Override
	public Offer createOffer(Offer offer) {
		offer.id = 5;
		offer.createdDate = new Date();
		offer.lastEditedDate = offer.createdDate;
		offer.isActive = true;
		offer.transactionClosed = false;
		offer.visitCount = 0;
		return offer;
	}

	@Override
	public Offer updateOffer(Offer offer) {
			offer.lastEditedDate = new Date();
			
		return offer;
	}

	@Override
	public void deleteOffer(Offer offer) {
		offer.isActive = false;
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public Offer findByOfferID(long offerID) {
		
		Offer offer = new Offer();
		
		offer.id = offerID; 
		offer.city = "Munich";
		offer.country = "Germany";
		offer.createdDate = new Date();
		offer.description = "a nice beatiful room to place anything";
		offer.header = "10 m² for low price";
		offer.houseNr = "42";
		offer.isActive = true;
		offer.lastEditedDate = offer.createdDate;
		offer.offerFrom = new Date();
		offer.offerTo = new Date(Date.UTC(2014, 6, 4, 23, 59, 59));
		
		 Person p = new Person();
			p.city = "Muenchen";
			p.country = "Deutschland";
			p.created = new Date();
			p.dateOfBirth = new Date();
			p.houseNr = "42";
			p.id = 6;
			p.isActive = true;
			p.isVerified = true;
			p.lastEdited = p.created;
			p.lastName = "Dummy";
			p.postCode = "12345";
			p.street = "Boltzmannstr";
			p.surname = "Peter";
			p.personSettings = new PersonSettings();
			p.personSettings.displayFirstNameOnly = false;
			p.personSettings.id = 3;
			p.personSettings.isActive = true;
			p.personSettings.person = p;
			p.personSettings.sendNewsletter = true;
		
		offer.owner = p;
		offer.street = "ABC Street";
		offer.subHeader = "really cheap storage!!!";
		offer.transactionClosed = false;
		offer.visitCount = 1337;
		
		return offer;
	}

	@Override
	public List<Offer> findByOwnerID(Person person) {
		
		List<Offer> o = new LinkedList<Offer>();
		
		
		for( int i = 0; i < 3; i++){
			Offer offer = new Offer();
			
			offer.id = i+42; 
			offer.city = "Munich";
			offer.country = "Germany";
			offer.createdDate = new Date();
			offer.description = "a nice beatiful room to place anything";
			offer.header = "10 m² for low price";
			offer.houseNr = "42";
			offer.isActive = true;
			offer.lastEditedDate = offer.createdDate;
			offer.offerFrom = new Date();
			offer.offerTo = new Date(Date.UTC(2014, 6, 4, 23, 59, 59));
			
			
			offer.owner = person;
			
			
			offer.street = "ABC Street";
			offer.subHeader = "really cheap storage!!!";
			offer.transactionClosed = false;
			offer.visitCount = 1337;
			
			o.add(offer);
		}
		
		
		return o;
	}

	@Override
	public List<Offer> findByAcceptorID(Person person) {
	List<Offer> o = new LinkedList<Offer>();
		
		
		for( int i = 0; i < 3; i++){
			Offer offer = new Offer();
			
			offer.id = i+42; 
			offer.city = "Munich";
			offer.country = "Germany";
			offer.createdDate = new Date();
			offer.description = "a nice beatiful room to place anything";
			offer.header = "10 m² for low price";
			offer.houseNr = "42";
			offer.isActive = true;
			offer.lastEditedDate = offer.createdDate;
			offer.offerFrom = new Date();
			offer.offerTo = new Date(Date.UTC(2014, 6, 4, 23, 59, 59));
			
			
			 Person p = new Person();
				p.city = "Muenchen";
				p.country = "Deutschland";
				p.created = new Date();
				p.dateOfBirth = new Date();
				p.houseNr = "42";
				p.id = 6;
				p.isActive = true;
				p.isVerified = true;
				p.lastEdited = p.created;
				p.lastName = "Dummy";
				p.postCode = "12345";
				p.street = "Boltzmannstr";
				p.surname = "Peter";
				p.personSettings = new PersonSettings();
				p.personSettings.displayFirstNameOnly = false;
				p.personSettings.id = 3;
				p.personSettings.isActive = true;
				p.personSettings.person = p;
				p.personSettings.sendNewsletter = true;
			
			offer.owner = p;
			offer.acceptor  = person;
			offer.contractedFrom = new Date();
			offer.contractedUntil = new Date();
			offer.transactionClosed = true;
			
			offer.street = "ABC Street";
			offer.subHeader = "really cheap storage!!!";
			offer.transactionClosed = false;
			offer.visitCount = 1337;
			
			o.add(offer);
		}
		
		
		return o;
	}

}
