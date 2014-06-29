package controllers;
import java.util.List;

import models.HomePageSearchForm;
import models.Offer;
import models.SearchAttributes;

import org.json.JSONObject;
import org.json.JsonGeoLocator;

import play.api.templates.Html;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import service.DiscoveryService;
import service.GeoLocationService;
import serviceImpl.DiscoveryServiceImpl;
import serviceImpl.GeoLocationServiceImpl;
import appinfo.GlobalValues;
/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class HomeController extends Controller {

//	private NewsService newsService = new NewsServiceImpl();//if the backend is ready switch to "..Impl" instead of "..Dummy"
	private static DiscoveryService discoveryService =  new DiscoveryServiceImpl();//if the backend is ready switch to "..Impl" instead of "..Dummy"
	private static GeoLocationService geoService = new GeoLocationServiceImpl();

	@Transactional
	public static Result index(){
		//menue bar
		Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_NOTHING);
		Html footer = views.html.footer.render();
		
		Form<HomePageSearchForm> searchForm = Form.form(HomePageSearchForm.class);
		
		
		Html content = views.html.home.render(searchForm,menubar, footer);
        return ok(content);
	}
	
	@Transactional
	public static Result search(){
		Html offerResults = null;
		Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_NOTHING);
		Form<HomePageSearchForm> searchForm = Form.form(HomePageSearchForm.class).bindFromRequest();
		HomePageSearchForm hpsf = searchForm.get();
		
		String[] postAction =request().body().asFormUrlEncoded().get("action");
		
		Result result = TODO;
		
		if (postAction == null || postAction.length == 0) {
			result = redirect(routes.HomeController.index());
		}else{
			String action = postAction[0];
			
			if(action.equals("submit")){
				
					String city = null;
					Double spacesize =null;
					Double radius = null;
					String postCode = null;
					SearchAttributes sa = new SearchAttributes();
					List<Offer> offers = null;
					
					//Check if necessary attributes are not null -> only start search if it is so
					if(hpsf.city.length()  > 3 || hpsf.postCode.length() > 3){
						System.out.println("[HOMECONTROLLER][search] yes");
						city = hpsf.city;
						postCode = hpsf.postCode;
						spacesize =hpsf.spacesize;
						radius = hpsf.radius;
						
						System.out.println("[HOMECONTROLLER][search] pc:"+postCode +" city: "+city);
						
						//fill search attributes object for data base query
						sa.postCode = postCode;
						sa.city = city;
						if(spacesize != null){
							sa.spaceSize = spacesize;
						}
						if(radius != null){
							sa.radius = radius;
						}
						
						geoService.calculateGeoCoords(sa);	
						
						offers = discoveryService.findOffersWithinRadius(sa);
						
						if(!offers.isEmpty()){
							offerResults = views.html.searchresults.render(offers);
						}
						System.out.println("count_ "+offers.size());
						
						result = ok(views.html.search.render(city,postCode,radius, spacesize, sa.lng, sa.lat, offerResults, menubar));
					}else{
						result = ok(views.html.search.render(null,null,null, null, null, null, null, menubar));
					}
					
					
					
					

			}else {
				result = redirect(routes.HomeController.index());
			}
		}
		return result;
	}


}