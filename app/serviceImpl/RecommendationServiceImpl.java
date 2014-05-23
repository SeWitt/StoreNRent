package serviceImpl;
import java.util.List;

import service.RecommendationService;
import repository.RecommendationRepository;
import model.Recommendation;
import model.RecommendationSummary;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recommendation updateRecommendation(Recommendation recomm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecommendation(Recommendation recomm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecommendationSummary getRecommendationSummary(long personID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recommendation> findRecommendationsByID(long personID) {
		// TODO Auto-generated method stub
		return null;
	}

	
}