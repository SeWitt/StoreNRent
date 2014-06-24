package controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import models.Offer;
import play.mvc.Controller;
import service.JSONService;
import serviceImpl.JSONServiceImpl;

/**
 * 
 * @author max
 *
 */
public class JSONController extends Controller {
	
	private static JSONService jsonService = new JSONServiceImpl();
	
	
	/**
	 * example method: how to interact with JSONService
	 */
	public static void dummyMethod() {
		
		// dummy offer with minimum amount of necessary properties
		Offer o = new Offer();
		o.header = "test header";
		o.offerFrom = new java.sql.Timestamp(System.currentTimeMillis());
		o.offerTo = new java.sql.Timestamp(System.currentTimeMillis());
		o.price = 4.0;
		o.spaceSize = 3.0;
		o.distance = 5.34252345;
		o.id = 1337;
		
		List<Offer> offers = new ArrayList<Offer>();
		offers.add(o);
		
		JsonObject response = jsonService.OfferListToJson(offers);
	}
	
	
	
	
}
