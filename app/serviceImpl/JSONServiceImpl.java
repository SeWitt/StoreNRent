package serviceImpl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import service.JSONService;
import models.Offer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * 
 * @author max
 *
 */
public class JSONServiceImpl implements JSONService{

	
	@Override
	public JsonObject OfferListToJson(List<Offer> offers) {

		String type = "type";
		String featureCollection = "FeatureCollection";
		String feature = "Feature";
		String features = "features";
		String properties = "properties";
		String header = "header";
		String fromDate = "fromDate";
		String toDate = "toDate";
		String price = "price";
		String size = "size";
		String distance = "distance";
		String id = "id";
		String geometry = "geometry";
		String point = "Point";
		String coordinates = "coordinates";
		
		DecimalFormat df = new DecimalFormat("#.##");      
		
		

		JsonObject root = new JsonObject();
		root.addProperty(type, featureCollection);
		
		JsonArray jOffers = new JsonArray();
		for(Offer offer : offers) {
			JsonObject jOffer = new JsonObject();
			jOffer.addProperty(type, feature);
			
			JsonObject attributes = new JsonObject();
			attributes.addProperty(header, offer.header);
			attributes.addProperty(fromDate, new SimpleDateFormat("dd.MM.yyyy").format(offer.offerFrom));	
			attributes.addProperty(toDate, new SimpleDateFormat("dd.MM.yyyy").format(offer.offerTo));		
			attributes.addProperty(price, offer.price);
			attributes.addProperty(size, offer.spaceSize);
			attributes.addProperty(distance, String.valueOf(df.format(offer.distance)));
			attributes.addProperty(id, offer.id);
			jOffer.add(properties, attributes);
			
			JsonObject geoLocation = new JsonObject();
			geoLocation.addProperty(type, point);
			JsonArray coords = new JsonArray();
			coords.add(new JsonPrimitive(offer.lng));
			coords.add(new JsonPrimitive(offer.lat));
			geoLocation.add(coordinates, coords);
			jOffer.add(geometry, geoLocation);
			
			jOffers.add(jOffer);
		}
		root.add(features, jOffers);
		
		return root;
	}

		
		
	
	@Override
	public String OfferListToJsonString(List<Offer> offers) {

		DecimalFormat df = new DecimalFormat("#.##");  

		String jsonHeader = "{\n\"type\": \"FeatureCollection\",\n\"features\": [\n";
		String jsonBottom = "\n]\n}";


		String tmp = jsonHeader;
		if(offers != null) {
			for(Offer o : offers) {
				tmp += "{ \"type\": \"Feature\", \"properties\": { \"header\": \"" + o.header + "\", \"fromDate\": \"" + new SimpleDateFormat("dd.MM.yyyy").format(o.offerFrom) +  "\", \"toDate\": \"" + new SimpleDateFormat("dd.MM.yyyy").format(o.offerTo) + "\", \"price\": \"" + o.price + "\", \"size\": \"" + o.spaceSize + "\", \"distance\": \"" + String.valueOf(df.format(o.distance)) + "\", \"id\": \"" + o.id + "\"}, \"geometry\": { \"type\": \"Point\", \"coordinates\": [ " + o.lng + ", " + o.lat + "  ] } }\n,\n";
			}	
			if (offers.size() > 0) {
				tmp = tmp.substring(0, tmp.length()-3);
			}
		}

		tmp += jsonBottom;
		
		return tmp;
	}
		
		
}