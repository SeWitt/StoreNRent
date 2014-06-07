package repository;
import java.util.List;

import javax.persistence.EntityManager;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import models.Account;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:16
 */
public class AccountRepository {
	
	EntityManager em;
	
	public AccountRepository(){
		em = JPA.em();
	}

	public void finalize() throws Throwable {

	}

	/**
	 * creates a new account
	 * 
	 * @param account
	 */
	@Transactional
	public Account createAccount(Account account){
		em.persist(account);
		em.flush();
		return account;
	}

	/**
	 * 
	 * @param account
	 */
	@Transactional
	public Account updateAccount(Account account){
		em.merge(account);
		em.flush();
		return account;
	}

	/**
	 * 
	 * @param mail
	 */
	@Transactional(readOnly=true)
	public Account findAccountByMail(String mail){
		List tmp = em.createQuery(
			    "SELECT c FROM ACCOUNT c WHERE c.mail LIKE :custMail")
			    .setParameter("custMail", mail)
			    .setMaxResults(10)
			    .getResultList();
		return (Account) tmp.get(0);
	}
	
	@Transactional
	public Account findAccountById(int id) {
		return em.find(Account.class, id);
	}

}