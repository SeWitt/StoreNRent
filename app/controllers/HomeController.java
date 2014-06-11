package controllers;
import java.util.List;

import models.HomePageSearchForm;
import models.Offer;
import models.Person;
import models.SearchAttributes;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import service.DiscoveryService;
import service.NewsService;
import service.OfferService;
import serviceDummy.DiscoveryServiceDummy;
import serviceDummy.NewsServiceDummy;
import serviceDummy.OfferServiceDummy;
import appinfo.GlobalValues;
/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class HomeController extends Controller {

	private NewsService newsService = new NewsServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"
	private static DiscoveryService discoveryService =  new DiscoveryServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"
	private static OfferService offerService = new OfferServiceDummy();

		
	public static Result index(){
		//menue bar
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
		Html footer = views.html.footer.render();
		
		Form<HomePageSearchForm> searchForm = Form.form(HomePageSearchForm.class);
		
		
		Html content = views.html.home.render(searchForm,menubar, footer);
        return ok(content);
	}
	
	
	public static Result search(){
		Html menubar = views.html.menubar.render(GlobalValues.NAVBAR_SEARCH);
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
					SearchAttributes sa = new SearchAttributes();
					List<Offer> offers = null;
					
					
					if(hpsf.city != null){
						city = hpsf.city;
						spacesize =hpsf.spacesize;
						
						sa.city = city;
						if(spacesize != null){
							sa.spaceSize = spacesize;
						}
						System.out.println("city: "+city);
						System.out.println("size: "+spacesize);
						//TODO: Replace through discovery service (real query, no dummy!)
						Person p = new Person();
				    	p.id = 2;
				    	p.lastName= "we";
				    	p.surname ="dd";
				    	offers = offerService.findByOwnerID(p);
					
				    	//TODO: Discovery service implementieren
//						offers = discoveryService.findOffers(sa);
					}
					
					result = ok(views.html.search.render(city, spacesize, offers, menubar));

			}else {
				result = redirect(routes.HomeController.index());
			}
		}
		return result;
	}


}