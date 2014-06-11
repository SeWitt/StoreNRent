package controllers;

import geo.google.GeoAddressStandardizer;
import geo.google.datamodel.GeoAddress;
import geo.google.datamodel.GeoCoordinate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import models.Offer;
import models.OfferAcceptForm;
import models.OfferForm;
import play.api.templates.Html;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Result;
import service.OfferService;
import service.RecommendationService;
import serviceDummy.OfferServiceDummy;
import serviceDummy.RecommendationServiceDummy;
import validators.TimeValidator;
import appinfo.GlobalValues;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class OfferController extends Controller {

	private static OfferService offerService = new OfferServiceDummy();// if the backend is ready switch to "..Impl" instead of "..Dummy"
	private RecommendationService recommendationService = new RecommendationServiceDummy();// if the backend is ready switch to "..Impl" instead of "..Dummy"

	public static Result index(Long id) {
		// menue bar
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);

		// search offer
		Offer o = offerService.findByOfferID(id.intValue());
		session("offerid", "" + o.id);
		// create acceptance form
		Form<OfferAcceptForm> offerForm = Form.form(OfferAcceptForm.class);
		OfferAcceptForm oaf = new OfferAcceptForm();
		oaf.from = new SimpleDateFormat(GlobalValues.DATEFORMAT)
				.format(new Timestamp(System.currentTimeMillis()));
		oaf.to = new SimpleDateFormat(GlobalValues.DATEFORMAT)
				.format(o.offerTo);
//		System.out.println("from index: " + oaf.from);
//		System.out.println("to index: " + oaf.to);
		offerForm.fill(oaf);

		Html content = views.html.offer.render(offerForm, menubar, o);
		return ok(content);
	}

	public static Result doAction() {

		Form<OfferAcceptForm> form = Form.form(OfferAcceptForm.class)
				.bindFromRequest();
		Result result = TODO;
		String[] postAction = request().body().asFormUrlEncoded().get("action");

		if (postAction == null || postAction.length == 0) {
//			System.out.println("null");
			result = redirect(routes.HomeController.index());
		} else {
			String action = postAction[0];

			if (action.equals("submit")) {
//				System.out.println("submit");
				int offerid = Integer.parseInt(session("offerid"));
				Offer o = offerService.findByOfferID(offerid);
				
			
				if (form.hasErrors()) {
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
		           
				 Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
				 result =  badRequest(views.html.offer.render(form, menubar, o));
			} else {
							
				OfferAcceptForm oaf = form.get();
				
				//make validation check whether until date is later than from:
				
				boolean isDatevalid = TimeValidator.isDateAfter(oaf.to, oaf.from,new SimpleDateFormat(GlobalValues.DATEFORMAT));
				boolean beginDateinside = TimeValidator.isDateAfterTSS(o.offerFrom, oaf.from,new SimpleDateFormat(GlobalValues.DATEFORMAT));
				boolean endDateinside = TimeValidator.isDateBeforeSTS(oaf.to, o.offerTo,new SimpleDateFormat(GlobalValues.DATEFORMAT));
				if((isDatevalid && beginDateinside && endDateinside) == false){
					Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
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
					return badRequest(views.html.offer.render(form, menubar, o));
				}
				//here all is valid!
				
				
				
				// o.acceptor = //TODO implement after authentication
				// o.contractedFrom = oaf.from;
				// o.contractedUntil = oaf.to;
				o.isActive = false;
				offerService.updateOffer(o);
				flash("success", "Congratulations");

//				System.out.println("from" + oaf.from);
//				System.out.println("to:" + oaf.to);
				// TODO: build offer transaction page (static to see successful
				// transaction
				result = TODO;

				 }
			} else if (action.equals("contact")) {
//				System.out.println("contact");

			} else if (action.equals("search")) {
//				System.out.println("search");
				result = redirect(routes.SearchController.search(null, null));
			} else if (action.equals("newOffer")) {
//				System.out.println("new");
				result = redirect(routes.OfferController.newOffer());
			}
		}

		return result;
	}

	public static Result newOffer() {

		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		Form<OfferForm> offerForm = Form.form(OfferForm.class);

		Html content = views.html.offerform.render(offerForm, menubar);
		return ok(content);

	}

	public static Result edit(Long id) {

		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		Offer o = offerService.findByOfferID(id.intValue());

		OfferForm of = new OfferForm(o);

		Form<OfferForm> offerForm = Form.form(OfferForm.class);
		offerForm = offerForm.fill(of);

		Html content = views.html.offerform.render(offerForm, menubar);
		return ok(content);
	}

	public static Result create() {

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
			           
					 Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
					 return badRequest(views.html.offerform.render(offerForm, menubar));
				} else {
					OfferForm of = offerForm.get();
					//make validation check whether until date is later than from:
					
					boolean isDatevalid = TimeValidator.isDateAfter(of.offerTo, of.offerFrom,new SimpleDateFormat(GlobalValues.DATEFORMAT));
					
					if(!isDatevalid){
						Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
						 offerForm.reject("date","invalid time span");
						flash("error", "The timespan is not ok! Start date has to be before end date!");
						return badRequest(views.html.offerform.render(offerForm, menubar));
					}
					//here all is valid!
					
					Offer o = new Offer(of);

					//download pictures
									
					try {
						// Initialize a new GeoAddressStandardizer-class with your API-Key
						GeoAddressStandardizer st = new GeoAddressStandardizer("AABBCC");
						String strAdd = 
								o.street +
								" " +
								o.houseNr +
								", " +
								o.postCode +
								" " + 
								o.city +
								", " +
								o.country;
						
						List<GeoAddress> addresses = st.standardizeToGeoAddresses(strAdd);
						GeoAddress address = addresses.get(0);
						GeoCoordinate coords = address.getCoordinate();
						double longitude = coords.getLongitude();
						double latitude = coords.getLatitude();
						o.geolocX = longitude;
						o.geolocY = latitude;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
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

//	public static Result getMapData() {
//		String resultString = "";
//		for (Offer o : offerService.findall()) {
//
//			String from;
//			String to;
//
//			if (o.offerFrom != null) {
//				from = new SimpleDateFormat(GlobalValues.TIMEFORMAT).format(o.offerFrom);
//			} else {
//				from = "NA";
//			}
//			if (o.offerTo != null) {
//				to = new SimpleDateFormat(GlobalValues.TIMEFORMAT).format(o.offerTo);
//			} else {
//				to = "NA";
//			}
//
//			resultString += " \"header\" : \"" + o.header + "\","+ System.lineSeparator();
//			resultString += " \"price\" : \"" + o.price + "\","+ System.lineSeparator();
//			resultString += " \"from\" : \"" + from + "\","+ System.lineSeparator();
//			resultString += " \"to\" : \"" + to + "\","+ System.lineSeparator();
//		}
//		renderJSON("[" + resultString.substring(1) + "]");
//	}

}
