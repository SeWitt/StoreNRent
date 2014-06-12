/**
 * 
 */
package serviceDummy;

import java.util.Collections;
import java.util.List;

import models.Offer;
import models.SearchAttributes;
import models.SortAttribute;
import repository.OfferRepository;
import service.DiscoveryService;

/**
 * @author Sebastian
 *
 */
public class DiscoveryServiceDummy implements DiscoveryService {

	@Override
	public List<Offer> findOffers(SearchAttributes sa) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public List<Offer> findOffersSortBy(SearchAttributes sa, SortAttribute param) {
		return null;
	}

}
