package service;

import models.Offer;
import models.SearchAttributes;

public interface GeoLocationService {

	/**
	 * in-place modification of provided SearchAttributes object
	 * 
	 * uses the post code and city of the provided SearchAttributes object to calculate its longitude and latitude
	 * the computation results will be added to the provided SearchAttribtes object (existing long. & lat. values will be overwritten)
	 * 
	 * if post code and/or city contain invalid values or no values at all the SearchAttributes object will be altered unpredictably
	 * 
	 * @param sa provided SearchAttributes object containing valid values for post code and city
	 */
	public void calculateGeoCoords(SearchAttributes sa); 
	
	/**
	 * in-place modification of provided Offer object
	 * 
	 * uses the country, post code, city, street name and house number of the provided Offer object to calculate its longitude and latitude
	 * the computation results will be added to the provided Offer object (existing long. & lat. values will be overwritten)
	 * 
	 * if country and/or post code and/or city and/or street name and/or house number contain invalid values or no values at all the Offer object will be altered unpredictably
	 * 
	 * @param o provided Offer object containing valid values for post code and city and country and street name and house number
	 */
	public void calculateGeoCoords(Offer o);
	
	public double getDistanceBetweenInKm(double lng1, double lat1, double lng2, double lat2, double altitude);
}
