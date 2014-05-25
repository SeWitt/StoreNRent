package repository;
import java.util.List;

import model.Person;
import model.Recommendation;
import model.RecommendationSummary;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:26
 */
public class RecommendationRepository {

	public RecommendationRepository(){

	}


	
	/**
	 * 
	 * @param recomm
	 */
	public Recommendation createRecommendation(Recommendation recomm){
		return null;
	}

	/**
	 * 
	 * @param recomm
	 */
	public Recommendation updateRecommendation(Recommendation recomm){
		return null;
	}

	/**
	 * 
	 * @param personID
	 */
	public RecommendationSummary getRecommendationSummary(Person person){
		return null;
	}

	/**
	 * 
	 * @param personID
	 */
	public List <Recommendation> findRecommendationByReceiver(Person person){
		return null;
	}

}