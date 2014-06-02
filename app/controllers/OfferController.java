package controllers;

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

	private OfferService offerService = new OfferServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"
	private RecommendationService recommendationService = new RecommendationServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"

	public OfferController(){

	}

	public static Result index() {
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		String title = "Garage for rent";
		Html content = views.html.offer.render(title, menubar);
        return ok(content);
    }

}