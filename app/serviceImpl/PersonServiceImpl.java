package serviceImpl;
import java.sql.Timestamp;
import java.util.Date;

import models.Person;
import service.PersonService;
import repository.PersonRepository;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:25
 */
public class PersonServiceImpl implements PersonService {

	private PersonRepository personRepo = new PersonRepository();;

	public PersonServiceImpl(){

	}

	@Override
	public Person createPerson(Person person) {
		person.isActive = true;
		person.created = new Timestamp(new Date().getTime());
		person.lastEdited = person.created;
		
		return personRepo.createPerson(person);
	}

	@Override
	public Person updatePerson(Person person) {
		if(person.isActive = true){
			person  = personRepo.updatePerson(person);
					
		}
		return person;
	}

	@Override
	public void deletePerson(Person person) {
		person.isActive  = false;
		personRepo.updatePerson(person);
		
	}

	@Override
	public Person findPersonByID(int personID) {
		return personRepo.findPersonByID(personID);
	}

	

}