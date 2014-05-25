package serviceDummy;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.News;
import service.NewsService;

public class NewsServiceDummy implements NewsService {

	@Override
	public List<News> getNews() {
		List<News> nl = new LinkedList<News>();
		String [] newsString = {"Store n Rent is now available at Munich!", "Cheap offers for Hinterhugelhapfing", "Sixt Car Rental becomes a partner "};
		
		for(int i = 0; i <newsString.length; i++ ){
			News n = new News();
			n.id = i;
			n.publishedDate = new Date();
			n.lastEditedDate = n.publishedDate;
			n.newsString = newsString[i]; 
			nl.add(n);
		}

		return nl;
	}
}
