package service;

import java.util.List;

import models.Offer;

import com.google.gson.JsonObject;

public interface JSONService {

	/**
	 * 
	 * @param offers list of models.Offer
	 * @return JsonObject containing all offers from param offers
	 */
	public JsonObject OfferListToJson(List<Offer> offers);

	/**
	 * 
	 * @param offers list of models.Offer
	 * @return String (valid Json format) containing all offers from param offers
	 */
	public String OfferListToJsonString(List<Offer> offers);
}