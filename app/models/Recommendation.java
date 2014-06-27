package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:25
 */
@Entity
@Table(name="RECOMMENDATION")
public class Recommendation {

	@Id
	@GeneratedValue
	@Column(name="RECOMMANDATION_ID")
	public long id;
	
	@Column(name="RECOMM_TYPE")
	public RecommendationType recommType;
	
	@ManyToOne
	@JoinColumn(name="RECEIVER_ID")
	public Person receiver;
	
	@ManyToOne
	@JoinColumn(name="TRANSMITTER_ID")
	public Person transmitter;
	
	@OneToOne
	@JoinColumn(name="OFFER_ID")
	public Offer intendedOffer;
	
	@Column(name="TEXT")
	public String text;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	public Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_EDITED_DATE")
	public Date lastEditedDate;
	
	@Column(name="IS_ACTIVE")
	public boolean isActive;

	public Recommendation(){

	}

	

}