package repository;
import java.util.List;

import javax.persistence.EntityManager;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import models.Offer;
import models.Person;
import models.SearchAttributes;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:22
 */
public class OfferRepository {

	EntityManager em;
	
	public OfferRepository(){
		em = JPA.em();
	}


	/**
	 * 
	 * @param offer
	 */
	@Transactional
	public Offer createOffer(Offer offer){
		em.persist(offer);
		em.flush();
		return offer;
	}

	/**
	 * 
	 * @param offer
	 */
	@Transactional
	public Offer updateOffer(Offer offer){
		em.merge(offer);
		em.flush();
		return offer;
	}

	/**
	 * 
	 * @param personID
	 */
	@Transactional
	public List<Offer> findOfferByOwner(Person owner){
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	@Transactional
	public Offer findOfferByID(long id){
		return em.find(Offer.class, id);
	}

	/**
	 * 
	 * @param personID
	 */
	@Transactional
	public List<Offer> findOfferByAcceptor(Person acceptor){
		return null;
	}

	/**
	 * 
	 * @param searchAttributs
	 */
	@Transactional
	public List<Offer> findOfferByAttributes(SearchAttributes searchAttributs){
		return null;
	}

}