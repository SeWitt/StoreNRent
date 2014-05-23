package model;

import java.sql.Date;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:25
 */
public class Recommendation {

	public long id;
	public RecommendationType recommType;
	public String text;
	public Date createdDate;
	public Date lastEditedDate;
	public boolean isActive;

	public Recommendation(){

	}

	

}