package controllers;
import play.mvc.Controller;
import service.MessageService;
import serviceDummy.MessageServiceDummy;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class MessageController extends Controller {

	private MessageService messageService = new MessageServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"

	public MessageController(){

	}



}