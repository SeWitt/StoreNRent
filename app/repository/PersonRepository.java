package repository;
import javax.persistence.EntityManager;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import models.Person;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:24
 */
public class PersonRepository {
	EntityManager em;

	public PersonRepository(){
		em = JPA.em();
	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param person
	 */
	@Transactional
	public Person createPerson(Person person){
		em.persist(person);
		em.flush();
		return person;
	}

	/**
	 * 
	 * @param person
	 */
	@Transactional
	public Person updatePerson(Person person){
		em.merge(person);
		em.flush();
		return person;
	}

	/**
	 * 
	 * @param id
	 */
	@Transactional(readOnly=true)
	public Person findPersonByID(long id){
		 return em.find(Person.class, id);
	}

}