package serviceImpl;
import java.util.List;

import models.Offer;
import models.SearchAttributes;
import service.DiscoveryService;
import repository.OfferRepository;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:18
 */
public class DiscoveryServiceImpl implements DiscoveryService {

	private OfferRepository offerRepo = new OfferRepository();

	public DiscoveryServiceImpl(){

	}

	@Override
	public List<Offer> findOffers(SearchAttributes sa) {
		// TODO Auto-generated method stub
		return null;
	}

	

}