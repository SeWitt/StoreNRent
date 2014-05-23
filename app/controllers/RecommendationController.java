package controllers;
import play.mvc.Controller;
import service.RecommendationService;
import serviceDummy.RecommendationServiceDummy;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class RecommendationController extends Controller {

	private RecommendationService recommendationService = new RecommendationServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"

	public RecommendationController(){

	}


}