package controllers;


import java.util.List;

import models.Offer;
import models.Person;
import play.*;
import play.mvc.*;
import service.OfferService;
import serviceDummy.OfferServiceDummy;

import views.html.*;


public class Application extends Controller {

	private static OfferService offerService = new OfferServiceDummy();
	
    public static Result index() {
    	
    	 response().setContentType("text/html");
    	 
    	return ok(search.render("Munich", searchmap.render(),  null));
//        return ok(index.render("Saudeppertes Play."));
    }
    
    public static Result searchResults(){
    	
    	Person p = new Person();
    	p.id = 2;
    	p.lastName= "we";
    	p.surname ="dd";
    	List<Offer> o = offerService.findByOwnerID(p);
    	return ok(searchresults.render(o)) ;
    }
    
    
   
    

}
