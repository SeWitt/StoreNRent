package models;

import java.sql.Timestamp;




/**
 * transient object (just for data collection, not for persistant storage)
 * 
 * stores all possible search attributes for one query
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:29
 */
//public class SearchAttributes implements PathBindable<SearchAttributes> {
	public class SearchAttributes  {


	public Timestamp from;
	public Timestamp to;
	public String city = "";
	public String postCode = ""; 
	public double spaceSize = 5;
	public double maxPrice = 15;
	public double radius = 1.5;
	public double lng;					// o.geolocX = longitude;
	public double lat;					// o.geolocY = latitude
//	@Override
	
	
	
	
	
//	public Either<String, SearchAttributes> bind(String arg0, String arg1) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public String javascriptUnbind() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public <B> Object transform(Function1<SearchAttributes, B> arg0,
//			Function1<B, SearchAttributes> arg1) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public String unbind(String arg0, SearchAttributes arg1) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
	
	
	
	
	
	
	
	
	
	
	
//Query String Pathbindable 
	
	
//	@Override
//	public Option<SearchAttributes> bind(String key, Map<String, String[]> data) {
//		if(data.containsKey(key+ ".from") && 
//			data.containsKey(key+ ".to") &&
//			data.containsKey(key+ ".city") &&
//			data.containsKey(key+ ".postCode") &&
//			data.containsKey(key+ ".spaceSize") &&
//			data.containsKey(key+ ".maxPrice") &&
//			data.containsKey(key+ ".radius") &&
//			data.containsKey(key+ ".lng") &&
//			data.containsKey(key+ ".lat")){
//			
//			try{
//				SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
//		         				
//				from = 		(Date) df.parse(data.get(key+ ".from")[0]);  
//				to =		(Date) df.parse(data.get(key+ ".to")[0]); 
//				city = 		data.get(key+ ".city") [0];
//				postCode =	data.get(key+ ".postCode") [0];
//				spaceSize = Double.parseDouble(data.get(key+ ".spaceSize") [0]);
//				maxPrice =	Double.parseDouble(data.get(key+ ".maxPrice")[0]);
//				radius = 	Double.parseDouble(data.get(key+ ".radius") [0]);
//				lng =		data.get(key+ ".lng") [0];
//				lat = 		data.get(key+ ".lat")[0];
//				
//				return Option.Some(this);
//			}catch(Exception e){
//				return Option.None();
//			}
//		}else{
//			return Option.None();
//		}
//
//		
//		
//	}
//	@Override
//	public String javascriptUnbind() {
//		return "function(k,v) {\n" +
//	             "    return encodeURIComponent(k+'.from')+'='+v.from+'&'" +
//	             "			+encodeURIComponent(k+'.city')+'='+v.city+'&'" +
//	             "			+encodeURIComponent(k+'.postCode')+'='+v.postCode+'&'" +
//	             "			+encodeURIComponent(k+'.spaceSize')+'='+v.spaceSize+'&'" +
//	             "			+encodeURIComponent(k+'.maxPrice')+'='+v.maxPrice+'&'" +
//	             "			+encodeURIComponent(k+'.radius')+'='+v.radius+'&'" +
//	             "			+encodeURIComponent(k+'.lng')+'='+v.lng+'&'" +
//	             "			+encodeURIComponent(k+'.lat')+'='+v.lat+'&'" +
//	             "			+encodeURIComponent(k+'.to')+'='+v.to;\n" +
//	             "}";
//	}
//	@Override
//	public String unbind(String key) {
//		return key+ ".from=" + from.toString() + "&" + 
//				key+ ".to=" + to.toString() + "&" +
//		key+ ".city" + city + "&" +
//		key+ ".postCode" + postCode + "&" +
//		key+ ".spaceSize" + spaceSize + "&" +
//		key+ ".maxPrice" + maxPrice + "&" +
//		key+ ".radius" + radius + "&" +
//		key+ ".lng" + lng + "&" +
//		key+ ".lat" + lat; 
//	}
	
	
	
	

}