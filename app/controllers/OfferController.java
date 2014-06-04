package controllers;

import models.Offer;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import service.OfferService;
import service.RecommendationService;
import serviceDummy.OfferServiceDummy;
import serviceDummy.RecommendationServiceDummy;
import serviceImpl.OfferServiceImpl;
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
	
	public static Result newOffer(){
		
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		Form<Offer> offerForm = Form.form(Offer.class);	
		
		Html content = views.html.offerform.render(offerForm,menubar);
        return ok(content);
		
		
	}
	
	
	public static Result edit(Long id) {

		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		Offer o = offerService.findByOfferID(id.longValue());
			
		Form<Offer> offerForm = Form.form(Offer.class);
		offerForm = offerForm.fill(o);
			
		Html content = views.html.offerform.render(offerForm,menubar);
        return ok(content);
    }
	
	public static Result create() {
		
		Form<Offer> offerForm = Form.form(Offer.class).bindFromRequest();
		
		String[] postAction =request().body().asFormUrlEncoded().get("action");
		
		Result result = TODO;
		
		if (postAction == null || postAction.length == 0) {
			result = redirect(routes.HomeController.index());
			System.out.println("postaction null");
		}else{
			String action = postAction[0];
			
			if(action.equals("submit")){
//					if(offerForm.hasErrors()){
//				Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
//					return badRequest(views.html.offerform.render(offerForm, menubar)); 
//				}else{
					Offer o = offerForm.get();
					o = offerService.createOffer(o);
					System.out.println("offer id:" +o.id);
					flash("success", "Successfully created!");
				
					System.out.println("submit pressed");

					result = redirect(routes.OfferController.index(o.id));
//				}
			}else if(action.equals("discard")){
				result = redirect(routes.HomeController.index());
				System.out.println("discard pressed");
			}
		}
		return result;
		
		
		
			

		
		
    }

	

}