package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PICTURE")
public class Picture {

	@Id
	@GeneratedValue
	@Column(name="PICTURE_ID")
	public int id;
	
	@Lob @Column(name="PICTURE")
	public byte[]  picture;
	
	@ManyToOne
	@JoinColumn(name="offer_id", referencedColumnName = "offer_id")
	public Offer offer;
	
	public Picture() {
		
	}
}
