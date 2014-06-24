package serviceImpl;
import geo.google.datamodel.GeoAltitude;
import geo.google.datamodel.GeoCoordinate;
import geo.google.datamodel.GeoUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Offer;
import models.SearchAttributes;
import models.SortAttribute;
import repository.OfferRepository;
import service.DiscoveryService;
import service.GeoLocationService;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:18
 */
public class DiscoveryServiceImpl implements DiscoveryService {
	
	private double altitudeMunich = 520.0;
	

//	private OfferRepository offerRepo = new OfferRepository();
	
	private GeoLocationService geoService = new GeoLocationServiceImpl();

	public DiscoveryServiceImpl(){

	}

	@Override
	public List<Offer> findOffers(SearchAttributes sa) {
		return findOffersSortBy(sa, null);
	}
	
	@Override
	public List<Offer> findOffersSortBy(SearchAttributes sa, SortAttribute param) {
		List<Offer> offers = OfferRepository.findOfferByAttributes(sa);
		offers = calculateDist(offers, sa);
		if(param != null) {
			Collections.sort(offers, new OfferComparator(param));
		} 
		return offers;
	}
	
	@Override
	public List<Offer> findOffersWithinRadius(SearchAttributes sa, SortAttribute param) {
		List<Offer> offers = findOffersSortBy(sa, param);
		
		int i = 0;
		for(Offer o : offers) {
			if(o.distance > sa.radius) {
				offers.remove(i);
			}
			i++;
		}
		return offers;
	}
	
	@Override
	public List<Offer> findOffersWithinRadius(SearchAttributes sa) {
		return findOffersWithinRadius(sa, null);
	}
	
	private List<Offer> calculateDist(List<Offer> offers, SearchAttributes sa) {
		for(Offer offer : offers) {
			offer.distance = geoService.getDistanceBetweenInKm(offer.lng, offer.lat, sa.lng, sa.lat, altitudeMunich);
		}
		return offers;
	}
	
	private class OfferComparator implements Comparator<Offer>{
		private SortAttribute param;
		
		public OfferComparator(SortAttribute param) {
			this.param = param;
		}

		@Override
		public int compare(Offer arg0, Offer arg1) {
			if(param.equals(SortAttribute.Distance)) {
				return Double.compare(arg0.distance, arg1.distance);
			}
			if(param.equals(SortAttribute.From)) {
				return Double.compare(arg0.offerFrom.getTime(), arg1.offerFrom.getTime());
			}
			if(param.equals(SortAttribute.To)) {
				return Double.compare(arg0.offerTo.getTime(), arg1.offerTo.getTime());
			}
			if(param.equals(SortAttribute.Price)) {
				return Double.compare(arg0.price, arg1.price);
			}
			if(param.equals(SortAttribute.Description)) {
				return arg0.description.compareTo(arg1.description);
			}
			if(param.equals(SortAttribute.Size)) {
				return Double.compare(arg0.spaceSize, arg1.spaceSize);
			}
			return 0;
		}
		
	}
	
	
	

	

}