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
	
	public AccountRepository(){
	}

	public void finalize() throws Throwable {

	}

	/**
	 * creates a new account
	 * 
	 * @param account 
	 */
	public Account createAccount(Account account){
		EntityManager em = JPA.em();
		em.persist(account);
		em.flush();
		return account;
	}

	/**
	 * updates an existing account
	 * 
	 * @param account
	 */
	public Account updateAccount(Account account){
		EntityManager em = JPA.em();
		em.merge(account);
		em.flush();
		return account;
	}

	/**
	 * find an account by email address
	 * 
	 * @param mail
	 */
	public Account findAccountByMail(String mail){
		EntityManager em = JPA.em();
		List tmp = em.createNativeQuery(
			    "Select * FROM ACCOUNT a where a.email = ?", Account.class)
			    .setParameter(1, mail)
			    .setMaxResults(10)
			    .getResultList();
		if(tmp != null) {
		return (Account) tmp.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * find account by id
	 * 
	 * @param id
	 * @return
	 */
	public Account findAccountById(int id) {
		EntityManager em = JPA.em();
		return em.find(Account.class, id);
	}

}