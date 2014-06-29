package service;

import java.util.List;

import models.Offer;
import models.SearchAttributes;
import models.SortAttribute;

public interface DiscoveryService {
	
	/**
	 * 
	 * @param sa provided SearchAttributes object containing valid values for post code, city, longitude, latitude and radius. 
	 * @return List of offers corresponding to the provided SearchAttributes object. Offers may be located outside of the provided radius.
	 */
	public List<Offer> findOffers(SearchAttributes sa);
	
	/**
	 * 
	 * @param sa provided SearchAttributes object containing valid values for post code, city, longitude, latitude and radius
	 * @param param result will be sorted by this attribute
	 * @return List of offers corresponding to the provided SearchAttributes object sorted by parameter param. Offers may be located outside of the provided radius.
	 */
	public List<Offer> findOffersSortBy(SearchAttributes sa, SortAttribute param);

	/**
	 * 
	 * @param sa provided SearchAttributes object containing valid values for post code, city, longitude, latitude and radius
	 * @param param result will be sorted by this attribute
	 * @return List of offers corresponding to the provided SearchAttributes object sorted by parameter param. 
	 */
	public List<Offer> findOffersWithinRadius(SearchAttributes sa, SortAttribute param);
	
	/**
	 * 
	 * @param sa provided SearchAttributes object containing valid values for post code, city, longitude, latitude and radius
	 * @return  List of offers corresponding to the provided SearchAttributes object.
	 */
	public List<Offer> findOffersWithinRadius(SearchAttributes sa);
}