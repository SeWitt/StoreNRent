package repository;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import models.Account;
import models.Person;
import models.Recommendation;
import models.RecommendationSummary;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:26
 */
public class RecommendationRepository {

	EntityManager em;
	
	public RecommendationRepository(){
		em = JPA.em();
	}


	
	/**
	 * 
	 * @param recomm
	 */
	@Transactional
	public Recommendation createRecommendation(Recommendation recomm){
		em.persist(recomm);
		em.flush();
		return recomm;
	}

	/**
	 * 
	 * @param recomm
	 */
	@Transactional
	public Recommendation updateRecommendation(Recommendation recomm){
		em.merge(recomm);
		em.flush();
		return recomm;
	}

	/**
	 * 
	 * @param personID
	 */
	@Transactional
	public RecommendationSummary getRecommendationSummary(Person person){
		List tmp = em.createQuery(
				"SELECT * FROM RECOMMENDATION_SUMMARY c WHERE c.person_id = ?", RecommendationSummary.class)
				.setParameter(1, person.id)
				.setMaxResults(10)
				.getResultList();
		if(tmp != null) {
			return (RecommendationSummary) tmp.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param personID
	 */
	@Transactional
	public List <Recommendation> findRecommendationByReceiver(Person person){
		List<Recommendation> tmp =  em.createQuery(
			    "SELECT * FROM RECOMMENDATION c WHERE c.RECEIVER_ID = ?", Recommendation.class)
			    .setParameter("custID", person.id)
			    .setMaxResults(10)
			    .getResultList();
		if (tmp != null) {
			return tmp;
		} else {
			return new ArrayList<Recommendation>();
		}
	}

}