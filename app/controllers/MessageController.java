package controllers;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class MessageController extends Controller {

//	private MessageService messageService = new MessageServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"

	@Transactional
	public static Result newMessage() {
		
		return TODO;
	}


}