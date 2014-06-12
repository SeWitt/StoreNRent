package serviceImpl;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Offer;
import models.SearchAttributes;
import models.SortAttribute;
import service.DiscoveryService;
import repository.OfferRepository;
import geo.google.datamodel.GeoAltitude;
import geo.google.datamodel.GeoCoordinate;
import geo.google.datamodel.GeoUtils;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:18
 */
public class DiscoveryServiceImpl implements DiscoveryService {

//	private OfferRepository offerRepo = new OfferRepository();

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
	
	private List<Offer> calculateDist(List<Offer> offers, SearchAttributes sa) {
		GeoAltitude munichAltitude = new GeoAltitude(520.0);
		for(Offer offer : offers) {
			GeoCoordinate gcO = new GeoCoordinate(offer.geolocX, offer.geolocY, munichAltitude);
			GeoCoordinate gcL = new GeoCoordinate(sa.lng, sa.lat, munichAltitude);
			offer.distance = GeoUtils.distanceBetweenInKm(gcO, gcL);
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
			return 0;
		}
		
	}

	

}