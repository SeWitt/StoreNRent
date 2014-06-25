package controllers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import models.HomePageSearchForm;
import models.Offer;
import models.OfferAcceptForm;
import models.OfferForm;
import models.Person;

import play.api.templates.Html;
import play.data.Form;
import play.data.validation.ValidationError;
import play.db.jpa.Transactional;
import play.mvc.*;


import service.AccountService;
import service.OfferService;
import serviceDummy.AccountServiceDummy;
import play.mvc.Controller;
import play.mvc.Result;
import service.GeoLocationService;
import serviceImpl.GeoLocationServiceImpl;
import serviceImpl.OfferServiceImpl;
import validators.TimeValidator;
import appinfo.GlobalValues;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class OfferController extends Controller {

	private static OfferService offerService = new OfferServiceImpl();
	private static AccountService accountService = new AccountServiceDummy();
//	private RecommendationService recommendationService = new RecommendationServiceDummy();// if the backend is ready switch to "..Impl" instead of "..Dummy"
	private static GeoLocationService geoService = new GeoLocationServiceImpl();
	
	@Transactional
	public static Result index(Integer id) {
		// menue bar
		Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_SEARCH);
		Html content = null;
		//set redirect
		session("url","/offer/show/"+id);
		// search offer

		try{
			Offer o = offerService.findByOfferID(id.intValue());
			
			//check whether it is the owner
			String isowner = isOwner(o);
	    	
			
			session("offerid", "" + id.toString());
			// create acceptance form
			Form<OfferAcceptForm> offerForm = Form.form(OfferAcceptForm.class);
			OfferAcceptForm oaf = new OfferAcceptForm();
			oaf.from = new SimpleDateFormat(GlobalValues.DATEFORMAT).format(new Timestamp(System.currentTimeMillis()));
			oaf.to = new SimpleDateFormat(GlobalValues.DATEFORMAT).format(o.offerTo);
//			System.out.println("from index: " + oaf.from);
//			System.out.println("to index: " + oaf.to);
			offerForm.fill(oaf);

			content = views.html.offer.render(offerForm, menubar, o, isowner);
		}catch(Exception e){
			flash("error", "Could not find a offer with id: "+id.intValue());
			return redirect(routes.SearchController.search(null, null, null, null));
		}
		
		return ok(content);
	}

	@Transactional
	public static Result doAction() {

		Form<OfferAcceptForm> form = Form.form(OfferAcceptForm.class).bindFromRequest();
		Result result = TODO;
		String[] postAction = request().body().asFormUrlEncoded().get("action");
		session("url",request().uri());
			
		if (postAction == null || postAction.length == 0) {

			result = redirect(routes.HomeController.index());
		} else {
			String action = postAction[0];

			if (action.equals("submit")) {

				int offerid = Integer.parseInt(session("offerid"));
				

				if (form.hasErrors()) {
					Offer o = offerService.findByOfferID(offerid);
					//check whether it is the owner
					String isowner = isOwner(o);
			    	
		            String errorMsg = "";
		            java.util.Map<String, List<play.data.validation.ValidationError>> errorsAll = form.errors();
		            
		            for (String field : errorsAll.keySet()) {
		                errorMsg += field + " ";
		                for (ValidationError error : errorsAll.get(field)) {
		                    errorMsg += error.message().toString() + ", ";
		                }
		            }
		            form.reject("date","invalid time span");
		            flash("error", "Please correct the following errors: " + errorMsg);
		           
		            Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_SEARCH);
		            result =  badRequest(views.html.offer.render(form, menubar, o, isowner));
				}else{
							
				OfferAcceptForm oaf = form.get();
				
				//make validation check whether until date is later than from:
//TODO: switch on validation, when valid bulk data are available				
//				boolean isDatevalid = TimeValidator.isDateAfter(oaf.to, oaf.from,new SimpleDateFormat(GlobalValues.DATEFORMAT));
//				boolean beginDateinside = TimeValidator.isDateAfterTSS(o.offerFrom, oaf.from,new SimpleDateFormat(GlobalValues.DATEFORMAT));
//				boolean endDateinside = TimeValidator.isDateBeforeSTS(oaf.to, o.offerTo,new SimpleDateFormat(GlobalValues.DATEFORMAT));
				
				
				boolean isDatevalid = true;
				boolean beginDateinside = true;
				boolean endDateinside = true;
				
				if((isDatevalid && beginDateinside && endDateinside) == false){
					Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_SEARCH);
					Offer o = offerService.findByOfferID(offerid);
					
					//check whether it is the owner
					String isowner = isOwner(o);
					
					String error = "";
					if(isDatevalid == false){
						error = "The timespan is not ok! Start date has to be before end date!";
					}
					if(beginDateinside == false){
						error += "The start date has to be inside the offer time span!";
					}
					if(endDateinside == false){
						error += "The end date has to be inside the offer time span!";
					}
					
					flash("error", "Please correct the following errors: " + error);
					return badRequest(views.html.offer.render(form, menubar, o, isowner));
				}
				//From her all Data are valid!
				
				String url = "/offer/accept/"+offerid+"/"+oaf.from+"/"+oaf.to;
				session("url",url);
				result = redirect(routes.OfferController.acceptOffer(offerid, oaf.from, oaf.to)); 

				 }
			} else if (action.equals("contact")) {
//				System.out.println("contact");
			} else if (action.equals("search")) {
//				System.out.println("search");
				result = redirect(routes.SearchController.search(null, null, null, null));
			} else if (action.equals("newOffer")) {
//				System.out.println("new");
				result = redirect(routes.OfferController.newOffer());
			} else if (action.equals("edit")) {
				
				int offerid = Integer.parseInt(session("offerid"));
				String url = "/offer/edit/"+offerid;
				session("url",url);
				result = redirect(routes.OfferController.edit(offerid));
			}
		}

		return result;
	}
	
	@Security.Authenticated(Secured.class)
	@Transactional
	public static Result acceptOffer(Integer id, String from, String to){
		SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalValues.DATEFORMAT);
		
		Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_SEARCH);
		Html footer = views.html.footer.render();
		Form<HomePageSearchForm> searchForm = Form.form(HomePageSearchForm.class);
		
		

		Offer o = offerService.findByOfferID(id);
		
		//DONETODO Set authenticated person as acceptor
		
		 o.acceptor = accountService.findAccountByMail(request().username()).person;
		 try {
			 o.contractedFrom = new Timestamp((dateFormat.parse(from)).getTime());
			 o.contractedUntil = new Timestamp((dateFormat.parse(to)).getTime());
			} catch (ParseException e) {}

		o.isActive = false;
		offerService.updateOffer(o);
		flash("success", "Congratulations, accepting the offer was successful!");



		Html content = views.html.home.render(searchForm,menubar, footer);
        return ok(content);
	}
	
	
	
	@Transactional
	public static Result newOffer() {
		session("url",request().uri());
		Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_SEARCH);
		Form<OfferForm> offerForm = Form.form(OfferForm.class);

		Html content = views.html.offerform.render(offerForm, menubar);
		return ok(content);

	}
	@Transactional
	@Security.Authenticated(Secured.class)
	public static Result edit(Integer id) {
		
		Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_SEARCH);
		Offer o = offerService.findByOfferID(id.intValue());

		OfferForm of = new OfferForm(o);

		Form<OfferForm> offerForm = Form.form(OfferForm.class);
		offerForm = offerForm.fill(of);

		Html content = views.html.offerform.render(offerForm, menubar);
		return ok(content);
	}
	
	@Transactional
	@Security.Authenticated(Secured.class)
	public static Result create() {
		Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_SEARCH);
		Form<OfferForm> offerForm = Form.form(OfferForm.class)
				.bindFromRequest();

		String[] postAction = request().body().asFormUrlEncoded().get("action");

		Result result = TODO;

		if (postAction == null || postAction.length == 0) {
			result = redirect(routes.HomeController.index());
		} else {
			String action = postAction[0];

			if (action.equals("submit")) {
				 if (offerForm.hasErrors()) {
			            String errorMsg = "";
			            java.util.Map<String, List<play.data.validation.ValidationError>> errorsAll = offerForm.errors();
			            for (String field : errorsAll.keySet()) {
			                errorMsg += field + " ";
			                for (ValidationError error : errorsAll.get(field)) {
			                    errorMsg += error.message().toString() + ", \n";
			                }
			            }
			            flash("error", "Please correct the following errors: " + errorMsg);
			           
					 return badRequest(views.html.offerform.render(offerForm, menubar));
				} else {
					
					
					OfferForm of = offerForm.get();
					
					//make validation check whether until date is later than from:				
					boolean isDatevalid = TimeValidator.isDateAfter(of.offerTo, of.offerFrom,new SimpleDateFormat(GlobalValues.DATEFORMAT));
					
					if(!isDatevalid){
						
						 offerForm.reject("date","invalid time span");
						flash("error", "The timespan is not ok! Start date has to be before end date!");
						return badRequest(views.html.offerform.render(offerForm, menubar));
					}
					
					//Now, all data are valid!
					
					Offer o = new Offer(of);
					
					
					o.owner =  accountService.findAccountByMail(session("email")).person;

					//TODO download pictures

					// get geo location
					// in place transformation of Offer o
					//
					geoService.calculateGeoCoords(o);

					o = offerService.createOffer(o);
					flash("success", "Successfully created!");

					result = redirect(routes.OfferController.index(o.id));
				}
			} else if (action.equals("discard")) {
				result = redirect(routes.HomeController.index());
			}
		}
		return result;
	}
	
	
	private static String isOwner(Offer o){
		//check whether it is the owner
		String isowner = null;

		//check if this is the owner
		String user = session("email");
//		System.out.println("[offercontroller][index user:] "+user);
		
		if (user != null){
			Person p = accountService.findAccountByMail(user).person;
			
//			System.out.println("[offercontroller][index user:] user is logged in");
//			System.out.println("[offercontroller][site   user:] "+p.id);
//			System.out.println("[offercontroller][offer owner:] "+o.owner.id);
			if(o.owner.id == p.id){
//				System.out.println("[offercontroller][index user:] is actual owner");
				isowner = "true";
			}
			
		}
		return isowner;
	}
	



}
