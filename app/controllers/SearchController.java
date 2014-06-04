package controllers;



import java.util.List;

import appinfo.GlobalValues;

import com.fasterxml.jackson.databind.JsonNode;

import models.Offer;
import models.Person;
import play.api.templates.Html;
import play.mvc.Controller;
import scala.Int;
import scala.Option;
import service.DiscoveryService;
import service.OfferService;
import serviceDummy.DiscoveryServiceDummy;
import serviceDummy.OfferServiceDummy;
import views.html.*;

import play.mvc.*;


;


/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class SearchController extends Controller {

	private static DiscoveryService discoveryService = new DiscoveryServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"
	private static OfferService offerService = new OfferServiceDummy();

	public static Result search(String city, Double spacesize){
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		if(city == null && spacesize == null){
			return ok(search.render(city, spacesize, null, menubar));
		}else{
			
			Person p = new Person();
	    	p.id = 2;
	    	p.lastName= "we";
	    	p.surname ="dd";
	    	List<Offer> o = offerService.findByOwnerID(p);
	
			
			return ok(search.render(city, spacesize,o , menubar));
		}
		
	}
	
	public static Result query(String fromdate, String todate, String city, String postcode, Double spacesize, Double maxprice, Double radius, String lng, String lat){
		
		Person p = new Person();
    	p.id = 2;
    	p.lastName= "we";
    	p.surname ="dd";
    	List<Offer> o = offerService.findByOwnerID(p);
		o.remove(0);
		System.out.println("ja ich wurde angesprochen");
		System.out.println(fromdate +" "+todate+" "+city+" "+postcode+" "+spacesize+" "+maxprice+" "+radius);
		return ok(searchresults.render(o));
	}
	
	
	
	
	
	
////	public static Result find(SearchAttribute sa){
////		return ok();
////	}
//	public static Result find(SearchAttribute sa){
//		return notFound();
//	}
	

}