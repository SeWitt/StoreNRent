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
	public class SearchAttributes  {


	public Timestamp from;
	public Timestamp to;
	public String city = "";
	public String postCode = ""; 
	public double spaceSize = 0;
	public double maxPrice = 0;
	public double radius = 1.5;
	public double lng;					
	public double lat;					
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchAttributes ["
				+ (from != null ? "from=" + from + ", " : "")
				+ (to != null ? "to=" + to + ", " : "")
				+ (city != null ? "city=" + city + ", " : "")
				+ (postCode != null ? "postCode=" + postCode + ", " : "")
				+ "spaceSize=" + spaceSize + ", maxPrice=" + maxPrice
				+ ", radius=" + radius + ", lng=" + lng + ", lat=" + lat + "]";
	}
	
}