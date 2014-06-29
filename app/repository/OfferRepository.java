package repository;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Offer;
import models.Person;
import models.Picture;
import models.SearchAttributes;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:22
 */
public class OfferRepository {

	public OfferRepository(){
		
	}


	/**
	 * 
	 * @param offer
	 */
	public static Offer createOffer(Offer offer){
		Person p = null;
		if(offer.owner == null) {
			List<Person> ps = PersonRepository.findAllPersons();
			if(ps.size()==0) {
				p = PersonRepository.getDummyPerson();
				PersonRepository.createPerson(p);
			} else {
				p = ps.get(0);
			}
			offer.owner = p;
		}
		EntityManager em = JPA.em();
		em.clear();
		em.persist(offer);
		em.flush();
		return offer;
	}

	/**
	 * 
	 * @param offer
	 */
	public static Offer updateOffer(Offer offer){
		EntityManager em = JPA.em();
		em.merge(offer);
		em.flush();
		return offer;
	}
	
	public static List<Offer> findAllOffers(){
		EntityManager em = JPA.em();
		List<Offer> tmp = em.createNativeQuery(
			    "SELECT * FROM offer", Offer.class)
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
	public static Offer findOfferByID(int id){
		EntityManager em = JPA.em();
		return em.find(Offer.class, id);
	}

	/**
	 * 
	 * @param personID
	 */
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
	public static List<Offer> findOfferByAttributes(SearchAttributes searchAttributs){
		System.out.println("[OfferRepository] [findOfferByAttributes] [" + searchAttributs.toString() + "]");
		EntityManager em = JPA.em();
		List<Offer> tmp = em.createNativeQuery(queryGenerator(searchAttributs), Offer.class)
			    .setMaxResults(100)
			    .getResultList();
		System.out.println("[OfferRepository] [findOfferByAttributes] [" + queryGenerator(searchAttributs) + "]");
		if(tmp != null) {
			return tmp;
		} else {
			return new ArrayList<Offer>();
		}
		
	}
	
	public static String queryGenerator(SearchAttributes sa) {
		
//		this section prevents unwanted search querys
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
			String notContracted1 = "(to_timestamp(" + sa.from.getTime()/1000 + ") not between o.contracted_from and o.contracted_until and to_timestamp(" + sa.to.getTime()/1000 + ") not between o.contracted_from and o.contracted_until)";
			String notContracted2 = "(o.contracted_from is null and o.contracted_until is null)";
			andNotContracted = "and (" + notContracted1 + " or " + notContracted2 + ")";
			andAvailable = "and to_timestamp(" + sa.from.getTime()/1000 + ") between o.offer_from and o.offer_to and to_timestamp(" + sa.to.getTime()/1000 + ") between o.offer_from and o.offer_to";
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
	
		System.out.println("[OfferRepository] [QueryGenerator] [" + result + "]");
			
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