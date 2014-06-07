package models;

import play.data.validation.Constraints.*;


public class OfferAcceptForm {

	@Required(message = "A start date is necessary!" )
	public String from;
	
	@Required(message = "An end date is required!" )
	public String to;

		
}
