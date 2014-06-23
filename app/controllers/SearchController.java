package controllers;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import models.Offer;
import models.SearchAttributes;
import models.SortAttribute;

import org.json.JSONObject;
import org.json.JsonGeoLocator;

import play.api.templates.Html;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.DiscoveryService;
import service.GeoLocationService;
import serviceImpl.DiscoveryServiceImpl;
import serviceImpl.GeoLocationServiceImpl;
import views.html.search;
import views.html.searchresults;
import appinfo.GlobalValues;


;


/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class SearchController extends Controller {

	private static DiscoveryService discoveryService = new DiscoveryServiceImpl();
	private static GeoLocationService geoService = new GeoLocationServiceImpl();


	/**initial search method
	 * 
	 * @param city city parameter
	 * @param radius radius
	 * @return a rendered html file
	 */
	@Transactional
	public static Result search(String city,String postCode, Double radius, Double spaceSize){

		Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_SEARCH);
		session("lasturl",request().uri());
		if(city == null && radius == null && postCode == null){
			return ok(search.render(city, postCode, radius, spaceSize, null,null,null, menubar));
		}else{
			
			SearchAttributes sa = new SearchAttributes();
			sa.city = city;
			sa.spaceSize = radius;
			//find offers
			List<Offer> o = discoveryService.findOffers(sa);
			Html offerResult = views.html.searchresults.render(o);
			return ok(search.render(city,postCode, radius,spaceSize , null, null, offerResult, menubar));
		}
	}
	
	/**search method 
	 * 
	 * Parameters:
	 * encapsulates all in an Searchattributes
	 * 
	 * @param fromdate
	 * @param todate
	 * @param city
	 * @param postcode
	 * @param spacesize
	 * @param maxprice
	 * @param radius
	 * @return
	 */
	@Transactional
	public static Result query(String fromdate, String todate, String city, String postcode, Double spacesize, Double maxprice, Double radius, Integer orderBy){
		
		SearchAttributes sa = new SearchAttributes();
		
		if(fromdate != null){
			try {
				sa.from =  new Timestamp((new SimpleDateFormat(GlobalValues.DATEFORMAT).parse(fromdate)).getTime());
			} catch (ParseException e) {}
		}
		
		if(todate != null){
			try {
				sa.to =  new Timestamp((new SimpleDateFormat(GlobalValues.DATEFORMAT).parse(todate)).getTime());
			} catch (ParseException e) {}
		}
		if(city != null){
			sa.city = city;
		}
		if(postcode != null){
			sa.postCode = postcode;
		}
		
		if(spacesize != null){
			sa.spaceSize = spacesize;
		}
		if(maxprice != null){
			sa.maxPrice = maxprice;
		}
		if(radius != null){
			sa.radius = radius;
		}
		
		
		
		//make geocoords
		//TODO: refactor -> exclude into new service
		
		// new Service for GeoCoords
		// in place alteration of sa (SearchAttributes)
		//
			
		
		// Legacy code below !!
		//
//		try {
//			String address = "  , "+					
//					sa.postCode +
//					" " + 
//					sa.city+
//					", ";
//			
//			final JSONObject response = JsonGeoLocator.getJSONByGoogle(address);
//	        if (response != null) {
//	        	JSONObject location = response.getJSONArray("results").getJSONObject(0);
//	        	location = location.getJSONObject("geometry");
//	            location = location.getJSONObject("location");
//	            double lng1 = location.getDouble("lng");// longitude
//	            double lat1 = location.getDouble("lat");// latitude
//	            System.out.println(String.format("%f, %f", lat1, lng1));
//	            
//	            sa.lng = lng1;
//				sa.lat = lat1;	            
//	        }
//		} catch (Exception e) {}

//PRODUCTIVE USE	
		geoService.calculateGeoCoords(sa);
		List<Offer> o = null;
//		try {

			if (orderBy > 0) {
				SortAttribute s = null;

				switch (orderBy) {
				case 1:
					s = SortAttribute.Distance;
					break;
				case 2:
					s = SortAttribute.Price;
					break;
				case 3:
					break;
				case 4:
					s = SortAttribute.From;
					break;
				case 5:
					s = SortAttribute.To;
					break;

				default:
					break;
				}

				if (s != null) {

					o = discoveryService.findOffersSortBy(sa, s);
				} else {
					o = discoveryService.findOffers(sa);
				}

			} else {
				o = discoveryService.findOffers(sa);
			}
//		} catch (Exception e) {
//			o = null;
//		}
			
		if(o.isEmpty()){
			o= null;
		}
		
//		System.out.println("DEBUG"+fromdate +" "+todate+" "+city+" "+postcode+" "+spacesize+" "+maxprice+" "+radius);
		return ok(searchresults.render(o));
	}
	

	
}