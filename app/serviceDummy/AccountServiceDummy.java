/**
 * 
 */
package serviceDummy;

import java.util.Date;

import model.Account;
import model.Person;
import model.PersonSettings;
import service.AccountService;
import service.PersonService;
import service.PersonalSettingsService;

/**
 * @author Sebastian
 *
 */
public class AccountServiceDummy implements AccountService {

	
	private PersonalSettingsService personalSettingsService = new PersonalSettingsServiceDummy(); //if the backend is ready switch to "..Impl" instead of "..Dummy"
	private PersonService personService = new PersonServiceDummy();//if the backend is ready switch to "..Impl" instead of "..Dummy"

	
	/* (non-Javadoc)
	 * @see serviceImpl.AccountService#createAccount(model.Account)
	 */
	public Account createAccount(Account account){
		
		account.createdDate = new Date();
		account.isActive = true;
		
		
		account.person = personService.createPerson(account.person);
		account.person.personSettings = personalSettingsService.createSettings(account.person.personSettings);
		
		
		return account;
	}

	/* (non-Javadoc)
	 * @see serviceImpl.AccountService#updateAccount(model.Account)
	 */
	public Account updateAccount(Account account){
		return account;
	}
	/* (non-Javadoc)
	 * @see serviceImpl.AccountService#deleteAccount(model.Account)
	 */
	public void deleteAccount(Account account){
	

	}

	/* (non-Javadoc)
	 * @see serviceImpl.AccountService#findAccountByMail(java.lang.String)
	 */
	public Account findAccountByMail(String mail){
		Account acc = new Account();
		acc.createdDate = new Date();
		acc.email = "mail@dummy.tum";
		acc.isActive = true;
		acc.password = "abcde";
		acc.person = new Person();
		acc.person.city = "Muenchen";
		acc.person.country = "Deutschland";
		acc.person.created = new Date();
		acc.person.dateOfBirth = new Date();
		acc.person.houseNr = "42";
		acc.person.id = 3;
		acc.person.isActive = true;
		acc.person.isVerified = true;
		acc.person.lastEdited = acc.person.created;
		acc.person.lastName = "Dummy";
		acc.person.postCode = "12345";
		acc.person.street = "Boltzmannstr";
		acc.person.surname = "Peter";
		acc.person.personSettings = new PersonSettings();
		acc.person.personSettings.displayFirstNameOnly = false;
		acc.person.personSettings.id = 3;
		acc.person.personSettings.isActive = true;
		acc.person.personSettings.person = acc.person;
		acc.person.personSettings.sendNewsletter = true;
		
		
		
		return acc;
	}


}
