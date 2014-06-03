package controllers;

import models.Offer;
import play.api.templates.Html;
import play.mvc.Controller;
import play.mvc.Result;
import service.OfferService;
import service.RecommendationService;
import serviceDummy.OfferServiceDummy;
import serviceDummy.RecommendationServiceDummy;
import appinfo.GlobalValues;


/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class OfferController extends Controller {

	private static OfferService offerService = new OfferServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"
	private RecommendationService recommendationService = new RecommendationServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"

	public OfferController(){

	}

	public static Result index(Long id) {
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		Offer o = offerService.findByOfferID(id.longValue());
		
		Html content = views.html.offer.render(menubar,o);
        return ok(content);
    }
	
	public static Result edit(Long id) {
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		Offer o = offerService.findByOfferID(id.longValue());
		
		Html content = views.html.offerform.render(menubar,o);
        return ok(content);
    }
	
	public static Result create() {
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		Offer o = new Offer();
		
		Html content = views.html.offer.render(menubar,o);
        return ok(content);
    }

	

}