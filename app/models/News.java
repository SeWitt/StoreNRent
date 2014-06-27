package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:20
 */

public class News {


	public int id;
	

	public String newsString;
	

	public Date publishedDate;
	

	public Date lastEditedDate;

	public News(){

	}

	
}