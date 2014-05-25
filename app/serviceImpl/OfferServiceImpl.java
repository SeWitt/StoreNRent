package serviceImpl;
import java.util.Date;
import java.util.List;

import model.Offer;
import model.Person;
import service.OfferService;
import service.PersonalSettingsService;
import serviceDummy.PersonalSettingsServiceDummy;
import repository.OfferRepository;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:23
 */
public class OfferServiceImpl implements OfferService {

	private PersonalSettingsService personalSettingsService = new PersonalSettingsServiceDummy(); //if the backend is ready switch to "..Impl" instead of "..Dummy"
	private OfferRepository offerRepo = new OfferRepository();;

	public OfferServiceImpl(){

	}

	@Override
	public Offer createOffer(Offer offer) {
		offer.createdDate = new Date();
		offer.lastEditedDate = offer.createdDate;
		offer.isActive = true;
		offer.transactionClosed = false;
		offer.visitCount = 0;
		return offerRepo.createOffer(offer);
	}

	@Override
	public Offer updateOffer(Offer offer) {
			if(offer.isActive = true){
				offer = offerRepo.updateOffer(offer);
			}
			
		return offer;
	}

	@Override
	public void deleteOffer(Offer offer) {
		offer.isActive = false;
		offerRepo.updateOffer(offer);
		
	}

	@Override
	public Offer findByOfferID(long offerID) {
		
		return offerRepo.findOfferByID(offerID);
	}

	@Override
	public List<Offer> findByOwnerID(Person person) {
		
		return offerRepo.findOfferByOwner(person);
	}

	@Override
	public List<Offer> findByAcceptorID(Person person) {
		return offerRepo.findOfferByAcceptor(person);
	}

	

}