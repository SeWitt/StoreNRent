package controllers;



import geo.google.GeoAddressStandardizer;
import geo.google.datamodel.GeoAddress;
import geo.google.datamodel.GeoCoordinate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import models.Offer;
import models.SearchAttributes;
import play.api.templates.Html;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.DiscoveryService;
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
//	private static OfferService offerService = new OfferServiceDummy();

	/**Method for the home site search
	 * 
	 * @param city city parameter
	 * @param radius radius
	 * @return a rendered html file
	 */
	@Transactional
	public static Result search(String city, Double radius){
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		if(city == null && radius == null){
			return ok(search.render(city, radius, null, menubar));
		}else{
			
			SearchAttributes sa = new SearchAttributes();
			sa.city = city;
			sa.spaceSize = radius;
			//find offers
			List<Offer> o = discoveryService.findOffers(sa);
	
			return ok(search.render(city, radius,o , menubar));
		}
		
	}
	
	/**search method for search site search
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
	 * @param lng
	 * @param lat
	 * @return
	 */
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

//PRODUCTIVE USE		
		List<Offer> o = discoveryService.findOffers(sa);
		
		System.out.println(fromdate +" "+todate+" "+city+" "+postcode+" "+spacesize+" "+maxprice+" "+radius);
		return ok(searchresults.render(o));
	}
	

	

}