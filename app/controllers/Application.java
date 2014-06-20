package controllers;


import java.io.Reader;
import java.sql.Clob;

import org.apache.commons.io.IOUtils;

import play.Logger;
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
    

}
