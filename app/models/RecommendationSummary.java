package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:29
 */
public class RecommendationSummary {

	
	public long id;
	
	
	public long positives;
	
	
	public long negatives;
	
	
	public long neutrals;
	
	
	public Person person;

	public RecommendationSummary(){

	}

	public void finalize() throws Throwable {

	}

}