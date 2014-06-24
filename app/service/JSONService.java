package service;

import java.util.List;

import models.Offer;

import com.google.gson.JsonObject;

public interface JSONService {

	public JsonObject OfferListToJson(List<Offer> offers);
}