package serviceImpl;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

import exception.InvalidCredentialsException;
import exception.UnkwonEmailException;

import repository.AccountRepository;
import service.AccountService;
import service.PersonService;
import service.PersonalSettingsService;
import serviceDummy.PersonServiceDummy;
import serviceDummy.PersonalSettingsServiceDummy;
import models.Account;

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
		String pwd = hashString(account.password);
		if (pwd != null){
			account.password = pwd;
		}
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
			
			
			String pwd = hashString(account.password);
			if (pwd != null){
				account.password = pwd;
			}

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



	@Override
	public int authenticate(String mail, String pwd) throws InvalidCredentialsException, UnkwonEmailException {

		hashString(pwd);
		Account acc = null;
		try{
			acc = findAccountByMail(mail);
		}catch(Exception e){
			throw new  UnkwonEmailException();
		}
			
		
		if(acc == null){
			throw new UnkwonEmailException();
		}
		
		int result = 0;

		if(checkPassword(pwd, acc.password) == true){
			result = acc.id;
		}else{
			throw new InvalidCredentialsException();
		}
		return result;
	}

	/**make a hash from string, concatenates with salt
	 * 
	 * @param clearString
	 * @return hash string
	 */
	private static String hashString(String clearString)  {
	  
		String salt = "$2a$10$5R7coBL0Z3Gl68LoTWVUXu ";	
	    String pwd =  BCrypt.hashpw(clearString, salt);
	    
//	    System.out.println("[AccountService][PWD hash salt:] "+salt);    
//	    System.out.println("[AccountServiceImpl[pwd hash] "+pwd);
	    
	    return pwd;
	}
	
	
	/**
	 * Method to check if entered user password is the same as the one that is
	 * stored (encrypted) in the database.
	 * 
	 * @param candidate
	 *            the clear text
	 * @param encryptedPassword
	 *            the encrypted password string to check.
	 * @return true if the candidate matches, false otherwise.
	 */
	private static boolean checkPassword(String candidate, String encryptedPassword) {
	    if (candidate == null) {
	        return false;
	    }
	    if (encryptedPassword == null) {
	        return false;
	    }
	    
	   String pwd = hashString(candidate);
	   pwd = pwd.trim();
	   encryptedPassword = encryptedPassword.trim();
	    
//	   System.out.println("[AccountService][hash user input:] "+pwd);    
//	   System.out.println("[AccountServiceImpl[pwd database:] "+encryptedPassword);
	   
	   
	    return pwd.equals(encryptedPassword);
	}
	
	
	
}