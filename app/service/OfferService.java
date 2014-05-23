package service;

import java.util.List;

import model.Offer;

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
	public abstract Offer findByOfferID(long offerID);

	/**
	 * 
	 * @param personID
	 */
	public abstract List<Offer> findByOwnerID(long personID);

	/**
	 * 
	 * @param personID
	 */
	public abstract List<Offer> findByAcceptorID(long personID);

}