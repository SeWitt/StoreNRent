package controllers;

import play.mvc.Controller;
import service.OfferService;
import service.RecommendationService;
import serviceDummy.OfferServiceDummy;
import serviceDummy.RecommendationServiceDummy;


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



}