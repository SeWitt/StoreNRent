package controllers;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.google.gson.JsonObject;

import models.Offer;
import models.SearchAttributes;
import models.SortAttribute;




import play.api.templates.Html;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.DiscoveryService;
import service.GeoLocationService;
import service.JSONService;
import service.OfferService;
import serviceImpl.DiscoveryServiceImpl;
import serviceImpl.GeoLocationServiceImpl;
import serviceImpl.JSONServiceImpl;
import serviceImpl.OfferServiceImpl;
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
	private static JSONService jsonService = new JSONServiceImpl();
	private static OfferService offerService = new OfferServiceImpl();


	/**initial search method
	 * 
	 * @param city city parameter
	 * @param radius radius
	 * @return a rendered html file
	 */
	@Transactional
	public static Result search(String city,String postCode, Double radius, Double spaceSize){

		Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_SEARCH);
		session("url","/search");
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
		
		
//PRODUCTIVE USE	
		geoService.calculateGeoCoords(sa);
		
		List<Offer> o = null;


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
					s = SortAttribute.Size;
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

					o = discoveryService.findOffersWithinRadius(sa, s);
				} else {
					o = discoveryService.findOffersWithinRadius(sa);
				}

			} else {
				o = discoveryService.findOffersWithinRadius(sa);
			}

			
		if(o.isEmpty()){
			o= null;
		}
		
//		System.out.println("DEBUG"+fromdate +" "+todate+" "+city+" "+postcode+" "+spacesize+" "+maxprice+" "+radius);
		return ok(searchresults.render(o));
	}
	
	/**Method to return a snippet of the current active offers in json format -> to display in map
	 * 
	 * @return Json format offer list + geoinformation
	 */
	@Transactional
	public static Result getMapData() {
		
		
//		String result ="{\"type\" : \"FeatureCollection\", \"features\": [{ \"type\" : \"Feature\", \"properties\" : { \"header\" : \"10 square meter for rent\", \"price\" : \"4 Euro\", \"id\" : \"3\"}, \"geometry\" : { \"type\" : \"Point\", \"coordinates\" : [ 11.539414536 , 48.125729304  ] } } ]}";
		
		
		List<Offer> o = offerService.findall();
		String result = jsonService.OfferListToJsonString(o);
		System.out.println("number: "+o.size());
		
//		JsonObject response = jsonService.OfferListToJson(o);
//		System.out.println("[SEARCH CONTROLLER][json:]"+response.getAsString());
		System.out.println(result);
		return  ok(result);
	}
	
}