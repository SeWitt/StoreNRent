package serviceImpl;
import model.Person;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person updatePerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person findPersonByID(long personID) {
		// TODO Auto-generated method stub
		return null;
	}

	

}