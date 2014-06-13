package controllers;



import geo.google.GeoAddressStandardizer;
import geo.google.datamodel.GeoAddress;
import geo.google.datamodel.GeoCoordinate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import models.Offer;
import models.Person;
import models.SearchAttributes;
import play.api.templates.Html;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.DiscoveryService;
import service.OfferService;
import serviceDummy.DiscoveryServiceDummy;
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
			// Initialize a new GeoAddressStandardizer-class with your API-Key
			GeoAddressStandardizer st = new GeoAddressStandardizer("AABBCC");
			String strAdd = "  , "+					
					sa.postCode +
					" " + 
					sa.city+
					", ";
			
			List<GeoAddress> addresses = st.standardizeToGeoAddresses(strAdd);
			GeoAddress address = addresses.get(0);
			GeoCoordinate coords = address.getCoordinate();
			double longitude = coords.getLongitude();
			double latitude = coords.getLatitude();
			sa.lng = longitude;
			sa.lat = latitude;
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