package service;

import java.util.List;

import models.Offer;
import models.SearchAttributes;
import models.SortAttribute;

public interface DiscoveryService {
	
	public List<Offer> findOffers(SearchAttributes sa);
	
	public List<Offer> findOffersSortBy(SearchAttributes sa, SortAttribute param);

}