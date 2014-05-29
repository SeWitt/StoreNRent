package service;

import java.util.List;

import models.Person;
import models.Recommendation;
import models.RecommendationSummary;

public interface RecommendationService {

	/**
	 * 
	 * @param recomm
	 */
	public abstract Recommendation createRecommendation(Recommendation recomm);

	/**
	 * 
	 * @param recomm
	 */
	public abstract Recommendation updateRecommendation(Recommendation recomm);

	/**
	 * 
	 * @param recomm
	 */
	public abstract void deleteRecommendation(Recommendation recomm);

	/**
	 * 
	 * @param personID
	 */
	public abstract RecommendationSummary getRecommendationSummary(Person person);

	
	/**
	 * 
	 * @param personID
	 */
	public abstract List<Recommendation> findRecommendationsByReceiver(Person person);

}