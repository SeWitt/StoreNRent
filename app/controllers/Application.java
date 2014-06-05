package controllers;


import play.Routes;
import play.mvc.Controller;
import play.mvc.Result;


public class Application extends Controller {

	
	
	
	
	public static Result jsRoutes()
	{
	    response().setContentType("text/javascript");
	    return ok(Routes.javascriptRouter("appRoutes", //appRoutes will be the JS object available in our view
	                                      routes.javascript.SearchController.query()));
	}
	
   
    

}
