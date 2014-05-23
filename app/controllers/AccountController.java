package controllers;

import play.mvc.Controller;
import service.AccountService;
import serviceDummy.AccountServiceDummy;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 17:26:15
 */
public class AccountController extends Controller {

	private AccountService accountService =  new AccountServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"

	public AccountController(){

	}


}