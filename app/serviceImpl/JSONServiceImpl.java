package serviceImpl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

		
		
		
		
}
		
/* ***************************************************************** */		
// 		//		// legacy code below	//		//		//
/* ***************************************************************** */
		
		
//		
//		String header = "10 square meter for rent";
//		double price = 4, lng = 11.0, lat = 48.0;
//		int id = 1;
//		
////		JSONObject j0 = new JSONObject().put("JSON", "Hello, World!");
////		System.out.println(j0.toString());
//		
////		JSONObject j1 = new JSONObject("{\"JSON\":\"Hello, World!\"}");
////		System.out.println(j1.toString());
//		
//		
//		
//		String jsonHeader = "{\n\"type\": \"FeatureCollection\",\n\"features\": [\n";
////		System.out.println(jsonHeader);
//		
//		String jsonBottom = "\n]\n}";
////		System.out.println(jsonBottom);
//		
//		
////		String value1 = "{ \"type\": \"Feature\", \"properties\": { \"HEADER\": \"10 square meter for rent\", \"PRICE\": \"4 Euro\", \"ID\": \"3\"}, \"geometry\": { \"type\": \"Point\", \"coordinates\": [ 11.539414536 , 48.125729304  ] } }\n";
//		
////		String value2 = "{ \"type\": \"Feature\", \"properties\": { \"HEADER\": \"" + header + "\", \"PRICE\": \"" + price + " Euro\", \"ID\": \"" + id + "\"}, \"geometry\": { \"type\": \"Point\", \"coordinates\": [ " + lng + ", " + lat + "  ] } }\n";
//		
////		System.out.println(jsonHeader + value1 + ",\n" + value2 + jsonBottom);
//		
//		
//		Offer o1 = new Offer();
//		o1.header = "das ist ein header";
//		o1.price = 4;
//		o1.id = 3;
//		o1.lng = 11.539414536;
//		o1.lat = 48.125729304;
//
//		Offer o2 = new Offer();
//		o2.header = "das ist ein header";
//		o2.price = 4;
//		o2.id = 3;
//		o2.lng = 11.539414536;
//		o2.lat = 48.125729304;
//
//		Offer o3 = new Offer();
//		o3.header = "das ist ein header";
//		o3.price = 4;
//		o3.id = 3;
//		o3.lng = 11.539414536;
//		o3.lat = 48.125729304;
//		
//		List<Offer> offers = new ArrayList<Offer>();
//		offers.add(o1);
////		offers.add(o2);
////		offers.add(o3);
//		
////		System.out.println(offerListToJSON(offers).toString());
//		
//		
////		JsArray aa = new Js("{\"type\": \"FeatureCollection\",\"features\": [{ \"type\": \"Feature\", \"properties\": { \"HEADER\": \"10 square meter for rent\", \"PRICE\": \"4 Euro\", \"ID\": \"3\"}, \"geometry\": { \"type\": \"Point\", \"coordinates\": [ 11.539414536 , 48.125729304  ] } }]}");
//
//		
//		
//	}
//	
////	public static JSONObject offerListToJSON(List<Offer> offers) {
////		String jsonHeader = "{\n\"type\": \"FeatureCollection\",\n\"features\": [\n";
////		String jsonBottom = "\n]\n}";
//		
////		String jsonHeader = "{\"type\": \"FeatureCollection\",\"features\": [";
////		String jsonBottom = "]}";
//		
////		JSONObject result = null;
//		
////		StringBuffer sb = new StringBuffer(jsonHeader);
////		String tmp = jsonHeader;
//		for(Offer o : offers) {
////			sb.append("{ \"type\": \"Feature\", \"properties\": { \"HEADER\": \"" + o.header + "\", \"PRICE\": \"" + o.price + " Euro\", \"ID\": \"" + o.id + "\"}, \"geometry\": { \"type\": \"Point\", \"coordinates\": [ " + o.lng + ", " + o.lat + "  ] } }\n,\n");
////			sb.append("{ \"type\": \"Feature\", \"properties\": { \"HEADER\": \"" + o.header + "\", \"PRICE\": \"" + o.price + " Euro\", \"ID\": \"" + o.id + "\"}, \"geometry\": { \"type\": \"Point\", \"coordinates\": [ " + o.lng + ", " + o.lat + "  ] } },");
////			tmp += "{ \"type\": \"Feature\", \"properties\": { \"HEADER\": \"" + o.header + "\", \"PRICE\": \"" + o.price + " Euro\", \"ID\": \"" + o.id + "\"}, \"geometry\": { \"type\": \"Point\", \"coordinates\": [ " + o.lng + ", " + o.lat + "  ] } },";
//		}
//		sb.subSequence(0, sb.length()-2);
//		sb.append(jsonBottom);
//		
//		tmp = tmp.substring(0, tmp.length()-1);
//		tmp += jsonBottom;
//		
//		System.out.println(sb.toString());
//		
//		System.out.println(tmp);
//		
//		result = new JSONObject(sb.toString());
//		result = new JSONObject(tmp);
//		return result;
//	}
//	
//
//	
//	

