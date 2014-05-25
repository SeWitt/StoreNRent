package serviceDummy;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.Offer;
import model.Person;
import model.PersonSettings;
import model.Recommendation;
import model.RecommendationSummary;
import model.RecommendationType;
import service.RecommendationService;

public class RecommendationServiceDummy implements RecommendationService {

	@Override
	public Recommendation createRecommendation(Recommendation recomm) {
		recomm.createdDate = new Date();
		recomm.isActive = true;
		recomm.lastEditedDate = recomm.createdDate;
		recomm.id = 42;
		
		return recomm;
	}

	@Override
	public Recommendation updateRecommendation(Recommendation recomm) {
		recomm.lastEditedDate = new Date();
		return recomm;
	}

	@Override
	public void deleteRecommendation(Recommendation recomm) {
		recomm.isActive = false;
		
		
	}

	@Override
	public RecommendationSummary getRecommendationSummary(Person person) {
		
		RecommendationSummary r = new RecommendationSummary();
		r.negatives = 3;
		r.personID = person.id;
		r.neutrals = 0;
		r.positives = 42;
		
		return r;
	}

	@Override
	public List<Recommendation> findRecommendationsByReceiver(Person person) {
		
		List<Recommendation> rL = new LinkedList<Recommendation>();
		
		String[] r = {"top gerne wieder", "hat alles super und einach geklappt", "gute Lage!", "seltsame gegend gewesen"};
		RecommendationType [] t = {RecommendationType.POSITIVE, RecommendationType.POSITIVE, RecommendationType.POSITIVE, RecommendationType.NEGATIVE};
		
		for(int i = 0; i < r.length; i++){
			Recommendation r2 = new Recommendation();
			r2.createdDate = new Date();
			r2.id = i;
					
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
				
				offer.owner = person;
				offer.acceptor  = p;
				offer.contractedFrom = new Date();
				offer.contractedUntil = new Date();
				offer.transactionClosed = true;
				
				offer.street = "ABC Street";
				offer.subHeader = "really cheap storage!!!";
				offer.transactionClosed = false;
				offer.visitCount = 1337;
			r2.intendedOffer = offer;
			r2.isActive = true;
			r2.lastEditedDate = new Date();
			r2.receiver = person;
			r2.recommType = t[i];
			r2.text = r[i];
			r2.transmitter = p;
				
			rL.add(r2);
		}
		
		return rL;
	}

}
