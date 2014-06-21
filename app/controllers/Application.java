package controllers;


import appinfo.GlobalValues;
import models.LoginForm;
import models.Person;
import play.Routes;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.api.mvc.Flash;
import play.api.templates.Html;
import play.db.jpa.Transactional;
import service.AccountService;
import serviceDummy.AccountServiceDummy;



public class Application extends Controller {

	private static AccountService as = new AccountServiceDummy();
	
	
	
	public static Result jsRoutes()
	{
	    response().setContentType("text/javascript");
	    return ok(Routes.javascriptRouter("appRoutes", //appRoutes will be the JS object available in our view
	                                      routes.javascript.SearchController.query()));
	}
	
//Login methods	
	public static Result login() {
	    return ok(views.html.login.render( Form.form(LoginForm.class)));
	}
	
	public static Result logout() {
		String url = session("lasturl");
		session().clear();
	    flash("success", "You've been logged out");

	    if(url == null){
	    	url = "/";
	    }
	    
	    return redirect(url);
	        
	    
	}
	
    
	public static Result authenticate() {
	    Form<LoginForm> loginForm =  Form.form(LoginForm.class).bindFromRequest();
	    if (loginForm.hasErrors()) {
	        return badRequest(views.html.login.render(loginForm));
	    } else {
	    	String url = session("url");
	        session().clear();
	        session("email", loginForm.get().email);
	        
	        System.out.println("[LOGIN] [authenticate] url: "+url);
	        if(url == null){
	        	url = "/";
	        }
	        
	        return redirect(url);	    
	    }
	}
	
	
	/**creates an menu bar snippet
	 * depends on the users login state
	 * 
	 * @param selected the to be selected ribbon
	 * @return an html menubar snippet
	 */
	public static Html getMenuebar(String selected){
		
		String name = null;
		
		String mail =request().username();
		if(mail != null){
			Person p = as.findAccountByMail(mail).person;
			name = p.surname +" "+ p.lastName;
		}
		
		
		Html menubar = views.html.menubar.render(selected,name,Form.form(LoginForm.class));
		
		return menubar;
	}
	
	
	
}
