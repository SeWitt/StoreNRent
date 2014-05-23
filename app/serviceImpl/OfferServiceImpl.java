package serviceImpl;
import java.util.List;

import model.Offer;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Offer updateOffer(Offer offer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOffer(Offer offer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Offer findByOfferID(long offerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> findByOwnerID(long personID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> findByAcceptorID(long personID) {
		// TODO Auto-generated method stub
		return null;
	}

}