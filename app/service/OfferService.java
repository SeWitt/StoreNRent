package service;

import java.util.List;

import models.Offer;
import models.Person;

public interface OfferService {

	/**
	 * 
	 * @param offer
	 */
	public abstract Offer createOffer(Offer offer);

	/**
	 * 
	 * @param offer
	 */
	public abstract Offer updateOffer(Offer offer);

	/**
	 * 
	 * @param offer
	 */
	public abstract void deleteOffer(Offer offer);

	/**
	 * 
	 * @param offerID
	 */
	public abstract Offer findByOfferID(int offerID);

	/**
	 * 
	 * @param personID
	 */
	public abstract List<Offer> findByOwnerID(Person person);

	/**
	 * 
	 * @param personID
	 */
	public abstract List<Offer> findByAcceptorID(Person person);

	public abstract List<Offer> findall();

}