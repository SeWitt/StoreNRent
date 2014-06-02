package controllers;


import java.util.List;


import models.Offer;
import models.Person;
import models.SearchAttributes;
import play.Routes;
import play.mvc.*;
import service.OfferService;
import serviceDummy.OfferServiceDummy;

import views.html.*;


public class Application extends Controller {

	
	
	
	
	public static Result jsRoutes()
	{
	    response().setContentType("text/javascript");
	    return ok(Routes.javascriptRouter("appRoutes", //appRoutes will be the JS object available in our view
	                                      routes.javascript.SearchController.query()));
	}
	
	
	
	
	
	
	
	
	
	
	
//	private static OfferService offerService = new OfferServiceDummy();
	
	
//    public static Result index() {
//    	
//    	 response().setContentType("text/html");
//    	 
//    	 SearchAttributes s = new SearchAttributes();
//    	 
//    	return ok(search.render("Munich", searchmap.render(),null, searchinput.render(s)));
////        return ok(index.render("Saudeppertes Play."));
//    }
//    
//    
//    
//    public static Result searchInput(){
//    	 SearchAttributes s = new SearchAttributes();
//    	return ok(searchinput.render(s));
//    }
//    
//    
//    public static Result searchResults(){
//    	
//    	Person p = new Person();
//    	p.id = 2;
//    	p.lastName= "we";
//    	p.surname ="dd";
//    	List<Offer> o = offerService.findByOwnerID(p);
//    	return ok(searchresults.render(o)) ;
//    }
    
    
   
    

}
