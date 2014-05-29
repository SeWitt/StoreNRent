package service;

import models.Account;

public interface AccountService {

	/**
	 * 
	 * @param account
	 */
	public abstract Account createAccount(Account account);

	/**
	 * 
	 * @param account
	 */
	public abstract Account updateAccount(Account account);

	/**
	 * 
	 * @param account
	 */
	public abstract void deleteAccount(Account account);

	/**
	 * 
	 * @param mail
	 */
	public abstract Account findAccountByMail(String mail);

}