package controllers;

import play.mvc.Controller;
import service.MessageService;
import service.PersonService;
import service.RecommendationService;
import serviceDummy.MessageServiceDummy;
import serviceDummy.PersonServiceDummy;
import serviceDummy.RecommendationServiceDummy;


/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class PersonController extends Controller {

	private PersonService personService = new PersonServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"
	private RecommendationService recommendationService = new RecommendationServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"
	private MessageService messageService = new MessageServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"

	public PersonController(){

	}

}