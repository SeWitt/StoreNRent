package validators;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import javax.validation.*;





@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = IsDateNotPasteValidator.class)
@play.data.Form.Display(name="constraint.isdatepaste")
public @interface IsDateNotPaste {
	 String message() default "Date is not ok";
	 String comparedDate() default "CURRENT";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
}
