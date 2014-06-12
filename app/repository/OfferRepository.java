package repository;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	@Transactional
	public static List<Offer> findAllOffers(){
		EntityManager em = JPA.em();
		List<Offer> tmp = em.createNativeQuery(
			    "SELECT * FROM offer", Offer.class)
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
	 * @param personID
	 */
	@Transactional
	public static List<Offer> findOfferByOwner(Person owner){
		EntityManager em = JPA.em();
		List<Offer> tmp =  em.createNativeQuery("SELECT * FROM offer o where o.owner_id = ?", Offer.class)
				.setParameter(1, owner.id)
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
		List<Offer> tmp = em.createNativeQuery(
			    "SELECT * FROM offer o where o.acceptor_id = ?", Offer.class)
			    .setParameter(1, acceptor.id)
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
		List<Offer> tmp = em.createNativeQuery(queryGenerator(searchAttributs), Offer.class)
			    .setMaxResults(10)
			    .getResultList();
		if(tmp != null) {
			return tmp;
		} else {
			return new ArrayList<Offer>();
		}
		
	}
	
	public static String queryGenerator(SearchAttributes sa) {
		
		String noResultQuery = "select * from offer where 1 = 2";
		
		if (sa.radius==0 || sa.lat==0 || sa.lng==0) {
			return noResultQuery;
		}
		
		if(sa.from == null && sa.to != null) {
			sa.from = sa.to;
		} 
		if(sa.from != null && sa.to == null) {
			sa.to = sa.from;
		}
		
		double[] bBox = boundingBoxGenerator(sa.lat, sa.lng, sa.radius);
		
		String selectFrom = "select * from offer o";
		String where = "where o.geolocX between " + bBox[2] + " and " + bBox[3] + " and o.geolocY between " + bBox[0] + " and " + bBox[1];
		String andIsActive = "and o.is_active = true";
		String andPrice = "and o.price <= " + sa.maxPrice;
		String andSpaceSize = "and o.space_size >= " + sa.spaceSize;
		String andNotContracted = "";
		String andAvailable = "";
		if(sa.from != null && sa.to != null) {
			andNotContracted = "and to_timestamp(" + sa.from.getTime() + ") not between o.contracted_from and o.contracted_until and to_timestamp(" + sa.to.getTime() + ") not between o.contracted_from and o.contracted_until";
			andAvailable = "and to_timestamp(" + sa.from.getTime() + ") between o.offer_from and o.offer_to and to_timestamp(" + sa.to.getTime() + ") between o.offer_from and o.offer_to";
		}
		
		String result = selectFrom + " " + where + " " + andIsActive;
		if(sa.maxPrice > 0) {
			result = result + " " + andPrice;
		}
		if(sa.spaceSize > 0) {
			result = result + " " + andSpaceSize;
		}
		if(sa.from != null) {
			result = result + " " + andNotContracted + " " + andAvailable;
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param lat latitude
	 * @param lng longitude
	 * @param rds radius in kilometers
	 * @return Array[0] = lower lat boundary; Array[1] = upper lat boundary; Array[2] = lower lng boundary; Array[3] = upper lng boundary
	 */
	public static double[] boundingBoxGenerator(double lat, double lng, double rds) {
//		lat = lat0 + (180/pi)*(dy/6378137)
//		lon = lon0 + (180/pi)*(dx/6378137)/cos(lat0)
//		Math.cos(Math.PI/180.0*lat0)
//		long correction factor: 1.000354
		final double pi = 3.14159265359;
		
		double[] result = new double[4];
		result[0] = lat - (180/pi)*((rds*1000)/6378137);
		result[1] = lat + (180/pi)*((rds*1000)/6378137);
		result[2] = (lng - (180/pi)*((rds*1000)/6378137)/Math.cos(Math.PI/180.0*lng)) * 1.000354;
		result[3] = (lng + (180/pi)*((rds*1000)/6378137)/Math.cos(Math.PI/180.0*lng)) * 1.000354;
		return result;
	}

}