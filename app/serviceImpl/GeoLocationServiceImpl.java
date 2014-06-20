package serviceImpl;

import geo.google.datamodel.GeoAltitude;
import geo.google.datamodel.GeoCoordinate;
import geo.google.datamodel.GeoUtils;

import org.json.JSONObject;
import org.json.JsonGeoLocator;

import models.Offer;
import models.SearchAttributes;
import service.GeoLocationService;

public class GeoLocationServiceImpl implements GeoLocationService{

	@Override
	public void calculateGeoCoords(SearchAttributes sa) {
		try {
			String address = "  , "+					
					sa.postCode +
					" " + 
					sa.city+
					", ";
	            double[] tmp = getLngandLat(address);
	            sa.lng = tmp[0];
				sa.lat = tmp[1];            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void calculateGeoCoords(Offer o) {
		try {
			String address = 
					o.street +
					" " +
					o.houseNr +
					", " +
					o.postCode +
					" " + 
					o.city +
					", " +
					o.country;
	            double[] tmp = getLngandLat(address);
	            o.lng = tmp[0];
				o.lat = tmp[1];            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private double[] getLngandLat(String address) {
		double[] result = new double[]{0.0, 0.0};
		
		final JSONObject response = JsonGeoLocator.getJSONByGoogle(address);
        if (response != null) {
        	try {
	        	JSONObject location = response.getJSONArray("results").getJSONObject(0);
	        	location = location.getJSONObject("geometry");
	            location = location.getJSONObject("location");
	            double lng1 = location.getDouble("lng");// longitude
	            double lat1 = location.getDouble("lat");// latitude
	            
	            result[0] = lng1;
				result[1] = lat1;
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        }	
			
		return result;
	}
	
	@Override
	public double getDistanceBetweenInKm(double lng1, double lat1, double lng2, double lat2, double altitude) {
		GeoAltitude a = new GeoAltitude(altitude);
		GeoCoordinate gcO = new GeoCoordinate(lng1, lat1, a);
		GeoCoordinate gcL = new GeoCoordinate(lng2, lat2, a);
		return GeoUtils.distanceBetweenInKm(gcO, gcL);
	}

}
