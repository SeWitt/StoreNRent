package repository;
import java.util.List;

import model.Offer;
import model.Person;
import model.SearchAttributes;

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
	public Offer createOffer(Offer offer){
		return null;
	}

	/**
	 * 
	 * @param offer
	 */
	public Offer updateOffer(Offer offer){
		return null;
	}

	/**
	 * 
	 * @param personID
	 */
	public List<Offer> findOfferByOwner(Person owner){
		return null;
	}

	/**
	 * 
	 * @param id
	 */
	public Offer findOfferByID(long id){
		return null;
	}

	/**
	 * 
	 * @param personID
	 */
	public List<Offer> findOfferByAcceptor(Person acceptor){
		return null;
	}

	/**
	 * 
	 * @param searchAttributs
	 */
	public List<Offer> findOfferByAttributes(SearchAttributes searchAttributs){
		return null;
	}

}