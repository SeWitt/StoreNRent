package serviceImpl;
import java.util.Date;
import java.util.List;

import service.RecommendationService;
import repository.RecommendationRepository;
import models.Person;
import models.Recommendation;
import models.RecommendationSummary;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:27
 */
public class RecommendationServiceImpl implements RecommendationService {

	private RecommendationRepository recommRepo = new RecommendationRepository();;

	public RecommendationServiceImpl(){

	}

	@Override
	public Recommendation createRecommendation(Recommendation recomm) {
		recomm.createdDate = new Date();
		recomm.isActive = true;
		recomm.lastEditedDate = recomm.createdDate;
		
		return recommRepo.createRecommendation(recomm);
	}

	@Override
	public Recommendation updateRecommendation(Recommendation recomm) {
		if(recomm.isActive = true){
			recomm.lastEditedDate = new Date();
			recomm = recommRepo.updateRecommendation(recomm);
		}
		return recomm;
	}

	@Override
	public void deleteRecommendation(Recommendation recomm) {
		recomm.isActive = false;
		recommRepo.updateRecommendation(recomm);
		
	}

	@Override
	public RecommendationSummary getRecommendationSummary(Person person) {
		return recommRepo.getRecommendationSummary(person);
	}

	@Override
	public List<Recommendation> findRecommendationsByReceiver(Person person) {
		return recommRepo.findRecommendationByReceiver(person);
	}

	

	
}