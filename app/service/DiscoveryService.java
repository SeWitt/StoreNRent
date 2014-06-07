package service;

import java.util.List;

import models.Offer;
import models.SearchAttributes;

public interface DiscoveryService {
	
	public List<Offer> findOffers(SearchAttributes sa);

}