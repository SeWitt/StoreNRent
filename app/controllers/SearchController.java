package controllers;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import models.Offer;
import models.SearchAttributes;

import org.json.JSONObject;
import org.json.JsonGeoLocator;

import play.api.templates.Html;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.DiscoveryService;
import service.OfferService;
import serviceDummy.OfferServiceDummy;
import serviceImpl.DiscoveryServiceImpl;
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

	private static DiscoveryService discoveryService = new DiscoveryServiceImpl();//if the backend is ready switch to "..Impl" instead of "..Dummy"
	private static OfferService offerService = new OfferServiceDummy();

	@Transactional
	public static Result search(String city, Double spacesize){
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		if(city == null && spacesize == null){
			return ok(search.render(city, spacesize, null, menubar));
		}else{
			
//			Person p = new Person();
//	    	p.id = 2;
//	    	p.lastName= "we";
//	    	p.surname ="dd";
//	    	List<Offer> o = offerService.findByOwnerID(p);
			
			SearchAttributes sa = new SearchAttributes();
			sa.city = city;
			sa.spaceSize = spacesize;
			
			List<Offer> o = discoveryService.findOffers(sa);
	
			
			return ok(search.render(city, spacesize,o , menubar));
		}
		
	}
	@Transactional
	public static Result query(String fromdate, String todate, String city, String postcode, Double spacesize, Double maxprice, Double radius, String lng, String lat){
		
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
		
		try {
			String address = "  , "+					
					sa.postCode +
					" " + 
					sa.city+
					", ";
			
			final JSONObject response = JsonGeoLocator.getJSONByGoogle(address);
	        if (response != null) {
	        	JSONObject location = response.getJSONArray("results").getJSONObject(0);
	        	location = location.getJSONObject("geometry");
	            location = location.getJSONObject("location");
	            double lng1 = location.getDouble("lng");// longitude
	            double lat1 = location.getDouble("lat");// latitude
	            System.out.println(String.format("%f, %f", lat1, lng1));
	            
	            sa.lng = lng1;
				sa.lat = lat1;	            
	        }
		} catch (Exception e) {}
//Just DEBUGGING, DUMMY		
//		Person p = new Person();
//    	p.id = 2;
//    	p.lastName= "we";
//    	p.surname ="dd";
//    	List<Offer> o = offerService.findByOwnerID(p);
//		o.remove(0);
//		System.out.println("ja ich wurde angesprochen");
		 
//PRODUCTIVE USE		
		List<Offer> o = discoveryService.findOffers(sa);
		
		System.out.println(fromdate +" "+todate+" "+city+" "+postcode+" "+spacesize+" "+maxprice+" "+radius);
		return ok(searchresults.render(o));
	}
	

	

}