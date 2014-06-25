package controllers;


import models.LoginForm;
import models.Person;
import models.Picture;
import play.Logger;
import play.Routes;
import play.api.templates.Html;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import repository.PictureRepository;
import service.AccountService;
import serviceImpl.AccountServiceImpl;
import exception.InvalidCredentialsException;
import exception.UnkwonEmailException;



public class Application extends Controller {

	private static AccountService as = new AccountServiceImpl();
	
	
	
	public static Result jsRoutes()
	{
	    response().setContentType("text/javascript");
	    return ok(Routes.javascriptRouter("appRoutes", //appRoutes will be the JS object available in our view
	    		 									routes.javascript.SearchController.getMapData(),	
	    											routes.javascript.SearchController.query()));
	}
	
	
	/**
	 * Called within HTML in order to render an image out of a <code>java.sql.Clob</code> by using 
	 * <br>
	 * <p><strong>&lt;img src=@{routes.Application.renderImage(pic)}&gt;</strong></p>
	 * where <code>pic</code> is the picture of an offer which attributes are to be 
	 * displayed on an <i>Offer</i> page.
	 * 
	 * @param pic the id of an picture of a offer stored in database
	 * @return a <code>play.mvc.Result</code> object
	 */
	@Transactional
	public static Result renderImage(Integer pic) {
		try {
			Picture picture = PictureRepository.findPictureByID(pic.intValue());
			if (picture != null) {
				return ok(picture.picture);
			}
		} catch (Exception e) {
			Logger.error("An IO Exception is occured while rendering image!", e);
		}
		return internalServerError("An IO Exception is occured while rendering image!");
	}
	
	
//Login methods	
	public static Result login() {
	    return ok(views.html.login.render( Form.form(LoginForm.class)));
	}
	
	public static Result logout() {
		String url = session("url");
		session().clear();
	    flash("success", "You've been logged out");

	    if(url == null){
	    	url = "/";
	    }
	    
	    return redirect(url);
	        
	    
	}
	
    @Transactional
	public static Result authenticate() {
	    Form<LoginForm> loginForm =  Form.form(LoginForm.class).bindFromRequest();
	    if (loginForm.hasErrors()) {
	        return badRequest(views.html.login.render(loginForm));
	    } else {
	    	
	    	String pwd = loginForm.get().password;
	    	String mail = loginForm.get().email;
	    	
	    	String url = session("url");
	       
	        
	        int result = -1;
	        try {
				 result = as.authenticate(mail, pwd);
			} catch (InvalidCredentialsException e) {
				flash("error", "Your credentials were not correct!");
				return badRequest(views.html.login.render(loginForm));
			}catch (UnkwonEmailException e2 ){
				flash("error", "Sorry we could not find an account!");
				return badRequest(views.html.login.render(loginForm));
			}
	        
	        
	        if(result > 0){
	        	 session().clear();
	        	 session("email", loginForm.get().email);
	        	 flash("success", "Login was successful!");
	        
	        	 System.out.println("[LOGIN] [authenticate] url: "+url);
	        	 if(url == null){
	        		 url = "/";
	        	 }
	        }else{
	        	flash("error", "Your credentials were incorrect");
				return badRequest(views.html.login.render(loginForm));
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
			System.out.println("[ApplicationController][name:]"+name);
			
		}
		
		
		Html menubar = views.html.menubar.render(selected,name,Form.form(LoginForm.class));
		
		return menubar;
	}
	
	
	
}
