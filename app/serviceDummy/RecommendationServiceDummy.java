package serviceDummy;

import java.util.List;

import model.Recommendation;
import model.RecommendationSummary;
import service.RecommendationService;

public class RecommendationServiceDummy implements RecommendationService {

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
