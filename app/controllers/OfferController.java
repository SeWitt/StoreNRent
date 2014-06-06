package controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import models.Offer;
import models.OfferAcceptForm;
import models.OfferForm;
import play.api.templates.Html;
import play.data.Form;
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


	
	

	public static Result index(Long id) {
		
		//menue bar
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		
		//search offer
		Offer o = offerService.findByOfferID(id.longValue());
		session("offerid",""+o.id);
		//create acceptance form
		Form<OfferAcceptForm> offerForm = Form.form(OfferAcceptForm.class);
		OfferAcceptForm oaf = new OfferAcceptForm();
		oaf.from = new SimpleDateFormat(GlobalValues.TIMEFORMAT).format(new Timestamp(System.currentTimeMillis()));
		oaf.to = new SimpleDateFormat(GlobalValues.TIMEFORMAT).format(o.offerTo);
		System.out.println("from index: "+oaf.from);
		System.out.println("to index: "+oaf.to);
		offerForm.fill(oaf);
		
		Html content = views.html.offer.render(offerForm,menubar,new OfferForm(o));
        return ok(content);
    }
	
	public static Result doAction(){
		
		Form<OfferAcceptForm> form = Form.form(OfferAcceptForm.class).bindFromRequest();
		Result result = TODO;
		String[] postAction =request().body().asFormUrlEncoded().get("action");

		
		if (postAction == null || postAction.length == 0) {
			System.out.println("null");
			result = redirect(routes.HomeController.index());
		}else{
			String action = postAction[0];
			
			if(action.equals("submit")){
				System.out.println("submit");
//					if(offerForm.hasErrors()){
//				Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
//					return badRequest(views.html.offerform.render(offerForm, menubar)); 
//				}else{
				long offerid = Long.parseLong(session("offerid"));
				Offer o = offerService.findByOfferID(offerid);
				OfferAcceptForm oaf =form.get();
//				o.acceptor = //TODO implement after authentication
//				o.contractedFrom = oaf.from;
//				o.contractedUntil = oaf.to;
				o.isActive=false;
				offerService.updateOffer(o);
				flash("success", "Congratulations");
				
				System.out.println("from"+oaf.from);
				System.out.println("to:"+oaf.to);
				//TODO: build offer transaction page (static to see successful transaction
				result = TODO;
			
//				}
			}else if(action.equals("contact")){
				System.out.println("contact");

			}else if(action.equals("search")){
				System.out.println("search");
				result = redirect(routes.SearchController.search(null, null));
			}else if(action.equals("newOffer")){
				System.out.println("new");
				result = redirect(routes.OfferController.newOffer());
			}
		}


		
		return result;
	}
	
	
	
	public static Result newOffer(){
		
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		Form<OfferForm> offerForm = Form.form(OfferForm.class);	
		
		Html content = views.html.offerform.render(offerForm,menubar);
        return ok(content);
		
		
	}
	
	
	public static Result edit(Long id) {

		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		Offer o = offerService.findByOfferID(id.longValue());
		
		OfferForm of = new OfferForm(o);
			
		Form<OfferForm> offerForm = Form.form(OfferForm.class);
		offerForm = offerForm.fill(of);
			
		Html content = views.html.offerform.render(offerForm,menubar);
        return ok(content);
    }
	
	public static Result create() {
		
	
		Form<OfferForm> offerForm = Form.form(OfferForm.class).bindFromRequest();
		
		String[] postAction =request().body().asFormUrlEncoded().get("action");
		
		Result result = TODO;
		
		if (postAction == null || postAction.length == 0) {
			result = redirect(routes.HomeController.index());
		}else{
			String action = postAction[0];
			
			if(action.equals("submit")){
					if(offerForm.hasErrors()){
//				Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
//					return badRequest(views.html.offerform.render(offerForm, menubar)); 
				}else{
				
					OfferForm of = offerForm.get();
					Offer o = new Offer(of);
					
					o = offerService.createOffer(o);
					flash("success", "Successfully created!");
				
					result = redirect(routes.OfferController.index(o.id));
				}
			}else if(action.equals("discard")){
				result = redirect(routes.HomeController.index());
			}
		}
		return result;
		
		
		
			

		
		
    }

	

}