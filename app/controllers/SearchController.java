package controllers;

import net.sf.ehcache.config.SearchAttribute;
import play.mvc.Controller;
import play.mvc.Result;
import service.DiscoveryService;
import serviceDummy.DiscoveryServiceDummy;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class SearchController extends Controller {

	private DiscoveryService discoveryService = new DiscoveryServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"

	public SearchController(){

	}
	
	public static Result find(SearchAttribute sa){
		return ok();
	}

	

}