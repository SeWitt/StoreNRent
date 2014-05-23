package service;

import model.Person;

public interface PersonService {

	/**
	 * 
	 * @param person
	 */
	public abstract Person createPerson(Person person);

	/**
	 * 
	 * @param person
	 */
	public abstract Person updatePerson(Person person);

	/**
	 * 
	 * @param person
	 */
	public abstract void deletePerson(Person person);

	/**
	 * 
	 * @param personID
	 */
	public abstract Person findPersonByID(long personID);

}