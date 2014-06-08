package repository;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import models.Account;
import models.Offer;
import models.Person;
import models.SearchAttributes;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:22
 */
public class OfferRepository extends Controller{

	public OfferRepository(){
		
	}


	/**
	 * 
	 * @param offer
	 */
	@Transactional
	public static Offer createOffer(Offer offer){
		EntityManager em = JPA.em();
		em.persist(offer);
		em.flush();
		return offer;
	}

	/**
	 * 
	 * @param offer
	 */
	@Transactional
	public static Offer updateOffer(Offer offer){
		EntityManager em = JPA.em();
		em.merge(offer);
		em.flush();
		return offer;
	}

	/**
	 * 
	 * @param personID
	 */
	@Transactional
	public static List<Offer> findOfferByOwner(Person owner){
		EntityManager em = JPA.em();
//		return em.createQuery(
//			    "SELECT c FROM OFFER c WHERE c.owner_id LIKE :custId")
//			    .setParameter("custId", owner.id)
//			    .setMaxResults(10)
//			    .getResultList();
		return em.createNativeQuery("Select OFFER from OFFER o where o.owner.person_id = ?1")
				.setParameter(1, owner.id)
				.setMaxResults(10)
				.getResultList();
	}

	/**
	 * 
	 * @param id
	 */
	@Transactional
	public static Offer findOfferByID(long id){
		EntityManager em = JPA.em();
		return em.find(Offer.class, id);
	}

	/**
	 * 
	 * @param personID
	 */
	@Transactional
	public static List<Offer> findOfferByAcceptor(Person acceptor){
		EntityManager em = JPA.em();
		List<Offer> tmp = em.createQuery(
			    "SELECT c FROM OFFER c WHERE c.acceptor_id LIKE :custId")
			    .setParameter("custId", acceptor.id)
			    .setMaxResults(10)
			    .getResultList();
		if(tmp != null) {
			return tmp;
		} else {
			return new ArrayList<Offer>();
		}
	}

	/**
	 * 
	 * @param searchAttributs
	 */
	@Transactional
	public static List<Offer> findOfferByAttributes(SearchAttributes searchAttributs){
		EntityManager em = JPA.em();
		return null;
	}

}