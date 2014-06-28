package repository;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Offer;
import models.Picture;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;

/**
 * @author Marius
 * @version 1.0
 * @created 20-Juni-2014 16:53:22
 */
public class PictureRepository extends Controller{

	public PictureRepository(){
		
	}


	/**
	 * 
	 * @param picture
	 */
	@Transactional
	public static Picture createPicture(Picture picture){
		EntityManager em = JPA.em();
		em.persist(picture);
		em.flush();
		return picture;
	}

	/**
	 * 
	 * @param picture
	 */
	@Transactional
	public static Picture updatePicture(Picture picture){
		EntityManager em = JPA.em();
		em.merge(picture);
		em.flush();
		return picture;
	}
	
	@Transactional
	public static List<Picture> findAllPictures(){
		EntityManager em = JPA.em();
		List<Picture> tmp = em.createNativeQuery(
			    "SELECT * FROM offer", Picture.class)
			    .getResultList();
		if(tmp != null) {
			return tmp;
		} else {
			return new ArrayList<Picture>();
		}
	}

	/**
	 * 
	 * @param offer
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public static List<Picture> findPictureByOffer(Offer offer){
		EntityManager em = JPA.em();
		List<Picture> tmp =  em.createNativeQuery("SELECT * FROM picture p where p.offer_id = ?", Picture.class)
				.setParameter(1, offer.id)
				.setMaxResults(10)
				.getResultList();
		if(tmp != null) {
			return tmp;
		} else {
			return new ArrayList<Picture>();
		}
	}

	/**
	 * 
	 * @param id
	 */
	@Transactional
	public static Picture findPictureByID(int id){
		EntityManager em = JPA.em();
		return em.find(Picture.class, id);
	}

}