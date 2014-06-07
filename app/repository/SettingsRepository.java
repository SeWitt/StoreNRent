package repository;
import java.util.List;

import javax.persistence.EntityManager;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import models.Account;
import models.Person;
import models.PersonSettings;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:30
 */
public class SettingsRepository {

	EntityManager em;
	
	public SettingsRepository(){
		em = JPA.em();
	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param setting
	 * @return 
	 */
	@Transactional
	public PersonSettings createSettings(PersonSettings setting){
		em.persist(setting);
		em.flush();
		return setting;

	}

	/**
	 * 
	 * @param settings
	 * @return 
	 */
	@Transactional
	public PersonSettings updateSettings(PersonSettings settings){
		em.merge(settings);
		em.flush();
		return settings;
	}

	/**
	 * 
	 * @param personID
	 */
	@Transactional
	public PersonSettings findSettingsByPersonID(Person person){
		List tmp = em.createQuery(
			    "SELECT c FROM PERSON_SETTINGS c WHERE c.person_id LIKE :custId")
			    .setParameter("custId", person.id)
			    .setMaxResults(10)
			    .getResultList();
		return (PersonSettings) tmp.get(0);
	}

}