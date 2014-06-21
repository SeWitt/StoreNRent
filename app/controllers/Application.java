package controllers;


import models.LoginForm;
import models.Person;

import java.io.Reader;
import java.sql.Clob;

import org.apache.commons.io.IOUtils;

import play.Logger;
import play.Routes;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.api.templates.Html;
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
	
	
	/**
	 * Called within HTML in order to render an image out of a <code>java.sql.Clob</code> by using 
	 * <br>
	 * <p><strong>&lt;img src=@{routes.Application.renderImage(offer.picX)}&gt;</strong></p>
	 * where <code>offer</code> is the offer which attributes are to be displayed on an <i>Offer</i>
	 * page and <code>offer.picX</code> is the <i>x</i>-th image of the <code>offer</code>'s 
	 * image-collection.
	 * 
	 * @param clob the image as a clob data object stored in database
	 * @return a <code>play.mvc.Result</code> object
	 * @see <a href="http://stackoverflow.com/questions/20317932/
	 * 		displaying-image-object-from-controller-in-the-browser/20838010#20838010">stackoverflow.com</a>
	 */
	public static Result renderImage(Clob clob) {
		try {
			Reader reader = clob.getCharacterStream();
			byte[] array = IOUtils.toByteArray(reader);
			return ok(array);
		} catch (Exception e) {
			Logger.error("An IO Exception is occured while extracting byte[] out of Clob!", e);
		}
		return internalServerError("An IO Exception is occured while extracting byte[] out of Clob!");
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
