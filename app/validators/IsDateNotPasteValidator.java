package validators;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;

import appinfo.GlobalValues;

import play.libs.F.Tuple;

public class IsDateNotPasteValidator extends play.data.validation.Constraints.Validator<Object> 
		implements ConstraintValidator<IsDateNotPaste, Object> {

	SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalValues.TIMEFORMAT);
	private String comparatorDate =  new SimpleDateFormat(GlobalValues.TIMEFORMAT).format( new Timestamp(System.currentTimeMillis()));
	private String comparedDate;
	
	private Timestamp comparatorTS;
	private Timestamp comparedTS;
	final static public String message = "The date is not ok!";

	@Override
	public void initialize(IsDateNotPaste annotation) {
		if(!annotation.comparedDate().equals("CURRENT")){
			comparatorDate = annotation.comparedDate();
		}
		try {
			comparatorTS = new Timestamp((dateFormat.parse(comparatorDate)).getTime());
		} catch (ParseException e) {
//			System.out.println("ging nicht");
		}
		
	}

	@Override
	public Tuple<String, Object[]> getErrorMessageKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid(Object object) {
		boolean retValue = false;
		if(object == null){
			return false;
		}
            

        if(!(object instanceof String)){
        	 return false;
        }
        
        try{
        	comparedDate = (String) object;
        	comparedTS = new Timestamp((dateFormat.parse(comparedDate)).getTime());
        	
        	retValue = comparedTS.after(comparatorTS);
        	
        	
        }catch(Exception e){
        	return false;
        }

		return retValue;
	}

}
