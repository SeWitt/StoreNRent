package service;

import models.Person;

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
	public abstract Person findPersonByID(int personID);

}