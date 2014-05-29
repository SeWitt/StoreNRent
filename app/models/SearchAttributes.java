package models;

import java.util.Date;

/**
 * transient object (just for data collection, not for persistant storage)
 * 
 * stores all possible search attributes for one query
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:29
 */
public class SearchAttributes {

	public SearchAttributes(){

	}

	public Date from;
	public Date to;
	public String city;
	public String postCode; 
	public double spaceSize;
	public double maxPrice;
	public double radius;
	public String lng;
	public String lat;
	

}