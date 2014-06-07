package serviceDummy;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import models.Offer;
import models.Person;
import models.PersonSettings;
import service.OfferService;
import service.PersonalSettingsService;

public class OfferServiceDummy implements OfferService {

	private PersonalSettingsService personalSettingsService = new PersonalSettingsServiceDummy(); //if the backend is ready switch to "..Impl" instead of "..Dummy"


	public OfferServiceDummy(){

	}

	@Override
	public Offer createOffer(Offer offer) {
		offer.id = 5;
		offer.createdDate = new Timestamp(System.currentTimeMillis());
		offer.lastEditedDate = offer.createdDate;
		offer.isActive = true;
		offer.transactionClosed = false;
		offer.visitCount = 0;
		return offer;
	}

	@Override
	public Offer updateOffer(Offer offer) {
			offer.lastEditedDate = new Timestamp(System.currentTimeMillis());
			
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
		offer.street = "Maximilianstraße";
		offer.houseNr = "12";
		offer.city = "München";
		offer.country = "Germany";
		offer.createdDate = new Timestamp(System.currentTimeMillis());
		offer.description = "a nice beatiful room to place anything";
		offer.header = "10 m³ for low price";
		offer.isActive = true;
		offer.lastEditedDate = offer.createdDate;
		offer.offerFrom = new Timestamp(System.currentTimeMillis());
		offer.offerTo = new Timestamp(2014, 12, 12, 23, 25, 50, 15);
		offer.price = 8.5;
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
			offer.createdDate = new Timestamp(System.currentTimeMillis());
			offer.description = "a nice beatiful room to place anything";
			offer.header = "10 m² for low price";
			offer.houseNr = "42";
			offer.isActive = true;
			offer.lastEditedDate = offer.createdDate;
			offer.offerFrom = new Timestamp(System.currentTimeMillis());
			offer.offerTo = new Timestamp(2014, 12, 12, 23, 25, 50, 15);
			
			offer.price = 8.5;
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
			offer.createdDate = new Timestamp(System.currentTimeMillis());
			offer.description = "a nice beatiful room to place anything";
			offer.header = "10 m² for low price";
			offer.houseNr = "42";
			offer.isActive = true;
			offer.lastEditedDate = offer.createdDate;
			offer.offerFrom = new Timestamp(System.currentTimeMillis());
			offer.offerTo = new Timestamp(2014, 12, 12, 23, 25, 50, 15);
			offer.price = 8.5;
			
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
			offer.contractedFrom =new Timestamp(System.currentTimeMillis());
			offer.contractedUntil =new Timestamp(System.currentTimeMillis());
			offer.transactionClosed = true;
			
			offer.street = "ABC Street";
			offer.subHeader = "really cheap storage!!!";
			offer.transactionClosed = false;
			offer.visitCount = 1337;
			
			o.add(offer);
		}
		
		
		return o;
	}

	@Override
	public List<Offer> findall() {
		List<Offer> ol = new LinkedList<Offer>();
		
		for(int i = 1; i <5; i++){
			ol.add(findByOfferID(i));
		}
		
		return ol;
	}

}
