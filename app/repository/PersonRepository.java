package repository;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import models.Offer;
import models.Person;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:24
 */
public class PersonRepository {

	public PersonRepository(){

	}

	
	/**
	 * 
	 * @param person
	 */
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
	public static Person updatePerson(Person person){
		EntityManager em = JPA.em();
		em.merge(person);
		em.flush();
		return person;
	}
	
	public static List<Person> findAllPersons() {
		EntityManager em = JPA.em();
		List<Person> tmp = em.createNativeQuery("select * from person p", Person.class).getResultList();
		if(tmp != null) {
			return tmp;
		} else {
			return new ArrayList<Person>();
		}
	}

	/**
	 * 
	 * @param id
	 */
	public static Person findPersonByID(int id){
		EntityManager em = JPA.em();
		return em.find(Person.class, id);
	}

	
	
	public static Person getDummyPerson() {
		Person p = new Person();
		p.city = "munich";
		p.country = "germany";
		p.created = new Timestamp(System.currentTimeMillis());
		p.dateOfBirth = new Timestamp(System.currentTimeMillis());
		p.houseNr = "1";
		p.isActive = true;
		p.isVerified = true;
		p.lastEdited = new Timestamp(System.currentTimeMillis());
		p.lastName = "von Haase";
		p.postCode = "07743";
		p.street = "Fürstengraben";
		p.surname = "Victor";
		return p;
	}
}