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
		Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_SEARCH);
		Html footer = views.html.footer.render();
		
		Form<HomePageSearchForm> searchForm = Form.form(HomePageSearchForm.class);
		
		
		Html content = views.html.home.render(searchForm,menubar, footer);
        return ok(content);
	}
	
	@Transactional
	public static Result search(){
		Html offerResults = null;
		Html menubar = Application.getMenuebar(GlobalValues.NAVBAR_SEARCH);
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
					if(hpsf.city != null && hpsf.postCode != null){
						city = hpsf.city;
						postCode = hpsf.postCode;
						spacesize =hpsf.spacesize;
						radius = hpsf.radius;
						
						//fill search attributes object for data base query
						sa.postCode = postCode;
						sa.city = city;
						if(spacesize != null){
							sa.spaceSize = spacesize;
						}
						if(radius != null){
							sa.radius = radius;
						}
						
						
						//make geocoords
						//TODO: refactor -> exclude into new service
						
						
						// new Service for GeoCoords
						// in place alteration of sa (SearchAttributes)
						//
						geoService.calculateGeoCoords(sa);	
						
						
						// Legacy code below !!
						//
//						try {
//							String address = "  , "+					
//									sa.postCode +
//									" " + 
//									sa.city+
//									", ";
//							
//							final JSONObject response = JsonGeoLocator.getJSONByGoogle(address);
//					        if (response != null) {
//					        	JSONObject location = response.getJSONArray("results").getJSONObject(0);
//					        	location = location.getJSONObject("geometry");
//					            location = location.getJSONObject("location");
//					            double lng1 = location.getDouble("lng");// longitude
//					            double lat1 = location.getDouble("lat");// latitude
//					            System.out.println(String.format("%f, %f", lat1, lng1));
//					            
//					            sa.lng = lng1;
//								sa.lat = lat1;	            
//					        }
//						} catch (Exception e) {}
						
//						//DEBUG
//						System.out.println("city: "+city);
//						System.out.println("postcode: "+postCode);
//						System.out.println("area: "+radius);

						offers = discoveryService.findOffers(sa);
						
						
						if(offers.isEmpty() != true){
							offerResults = views.html.searchresults.render(offers);
						}
						System.out.println("count_ "+offers.size());
					}
					
					
					
					result = ok(views.html.search.render(city,postCode,radius, spacesize, sa.lng, sa.lat, offerResults, menubar));

			}else {
				result = redirect(routes.HomeController.index());
			}
		}
		return result;
	}


}