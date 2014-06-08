package repository;
import javax.persistence.EntityManager;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import models.Person;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:24
 */
public class PersonRepository extends Controller{
	//	private static EntityManager em = JPA.em();

	public PersonRepository(){

	}

	//	public void finalize() throws Throwable {
	//
	//	}

	/**
	 * 
	 * @param person
	 */
	@Transactional
	public static Person createPerson(Person person){
		EntityManager em = JPA.em();
		em.persist(person);
		em.flush();
		return person;
	}

	/**
	 * 
	 * @param person
	 */
	@Transactional
	public static Person updatePerson(Person person){
		EntityManager em = JPA.em();
		em.merge(person);
		em.flush();
		return person;
	}

	/**
	 * 
	 * @param id
	 */
	@Transactional(readOnly=true)
	public static Person findPersonByID(int id){
		EntityManager em = JPA.em();
		return em.find(Person.class, id);
	}

}