package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:20
 */
@Entity
@Table(name ="NEWS")
public class News {

	@Id
	@GeneratedValue
	@Column(name="NEWS_ID")
	public long id;
	
	@Column(name="NEWS_STRING")
	public String newsString;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PUBLISHED_DATE")
	public Date publishedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_EDITED_DATE")
	public Date lastEditedDate;

	public News(){

	}

	
}