package serviceImpl;
import java.util.Date;

import repository.AccountRepository;
import service.AccountService;
import service.PersonService;
import service.PersonalSettingsService;
import serviceDummy.PersonServiceDummy;
import serviceDummy.PersonalSettingsServiceDummy;
import model.Account;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:17
 */
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepo = new AccountRepository();
	private PersonalSettingsService personalSettingsService = new PersonalSettingsServiceDummy(); //if the backend is ready switch to "..Impl" instead of "..Dummy"
	private PersonService personService = new PersonServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"

	public AccountServiceImpl(){

	}



	/* (non-Javadoc)
	 * @see serviceImpl.AccountService#createAccount(model.Account)
	 */
	public Account createAccount(Account account){
		
		account.createdDate = new Date();
		account.isActive = true;
		
		account = accountRepo.createAccount(account);
		
		account.person = personService.createPerson(account.person);
		account.person.personSettings = personalSettingsService.createSettings(account.person.personSettings);
		
		
		return account;
	}

	/* (non-Javadoc)
	 * @see serviceImpl.AccountService#updateAccount(model.Account)
	 */
	public Account updateAccount(Account account){
		if(account.isActive = true){
			account = accountRepo.updateAccount(account);

		}
		return account;
		}

	/* (non-Javadoc)
	 * @see serviceImpl.AccountService#deleteAccount(model.Account)
	 */
	public void deleteAccount(Account account){
		account.isActive = false;
		
		personalSettingsService.deletePersonalSettings(account.person.personSettings);
		personService.deletePerson(account.person);
		
		accountRepo.updateAccount(account);

	}

	/* (non-Javadoc)
	 * @see serviceImpl.AccountService#findAccountByMail(java.lang.String)
	 */
	public Account findAccountByMail(String mail){
		return accountRepo.findAccountByMail(mail);
	}

}