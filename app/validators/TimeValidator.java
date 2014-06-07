package validators;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeValidator {

	
	
	
	/**
	 * 
	 * @param firstDate the date which is compared to second date
	 * @param secondDate
	 * @param dateFormat the date time format
	 * @return  true if first > second
 	 */
	public static boolean isDateAfter(String firstDate, String secondDate, SimpleDateFormat dateFormat){
		
		boolean retValue =false;
		try {
			Timestamp comparator = new Timestamp((dateFormat.parse(firstDate)).getTime());
			Timestamp compared = new Timestamp((dateFormat.parse(secondDate)).getTime());
			
			retValue = comparator.after(compared);
			
		} catch (ParseException e) {
			return false;
		}
		
		
		
		
		
		return retValue;
	}
	
	/**
	 * 
	 * @param firstDate the date which is compared to second date
	 * @param secondDate
	 * @param dateFormat the date time format
	 * @return  true if first < second
 	 */
	public static boolean isDateBefore (String firstDate, String secondDate, SimpleDateFormat dateFormat){
		
		boolean retValue =false;
		try {
			Timestamp comparator = new Timestamp((dateFormat.parse(firstDate)).getTime());
			Timestamp compared = new Timestamp((dateFormat.parse(secondDate)).getTime());
			
			retValue = comparator.before(compared);
			
		} catch (ParseException e) {
			return false;
		}
		
		return retValue;
	}


	
	/**
	 * 
	 * @param firstDate the date which is compared to second date
	 * @param secondDate
	 * @param dateFormat the date time format
	 * @return  true if first < second
 	 */
	public static boolean isDateBeforeSTS (String firstDate, Timestamp secondDate, SimpleDateFormat dateFormat){
		
		boolean retValue =false;
		try {
			Timestamp comparator = new Timestamp((dateFormat.parse(firstDate)).getTime());
			
			retValue = comparator.before(secondDate);
			
		} catch (ParseException e) {
			return false;
		}
		
		return retValue;
	}

	
	
	
	/**
	 * 
	 * @param firstDate the date which is compared to second date
	 * @param secondDate
	 * @param dateFormat the date time format
	 * @return  true if first < second
 	 */
	public static boolean isDateAfterTSS(Timestamp firstDate, String secondDate,SimpleDateFormat dateFormat) {
		boolean retValue =false;
		try {

			Timestamp compared = new Timestamp((dateFormat.parse(secondDate)).getTime());
			
			retValue = firstDate.before(compared);
			
		} catch (ParseException e) {
			return false;
		}
		return retValue;
	}
}
