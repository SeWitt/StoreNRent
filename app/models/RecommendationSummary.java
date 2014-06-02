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
@Entity
@Table(name="RECOMMENDATION_SUMMARY")
public class RecommendationSummary {

	@Id
	@GeneratedValue
	@Column(name="RECOOMANDATION_SUMMARY_ID")
	public long id;
	
	@Column(name="POSITIVES")
	public long positives;
	
	@Column(name="NEGATIVES")
	public long negatives;
	
	@Column(name="NEUTRALS")
	public long neutrals;
	
	@OneToOne
	@JoinColumn(name="PERSON_ID")
	public Person person;

	public RecommendationSummary(){

	}

	public void finalize() throws Throwable {

	}

}