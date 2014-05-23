package serviceDummy;

import java.util.List;

import model.Offer;
import service.OfferService;

public class OfferServiceDummy implements OfferService {

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
