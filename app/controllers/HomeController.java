package controllers;
import play.mvc.Controller;
import service.DiscoveryService;
import service.NewsService;
import serviceDummy.DiscoveryServiceDummy;
import serviceDummy.NewsServiceDummy;


import play.mvc.*;
/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class HomeController extends Controller {

	private NewsService newsService = new NewsServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"
	private DiscoveryService discoveryService =  new DiscoveryServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"


		
	public static Result index(){
		return TODO;
	}


}