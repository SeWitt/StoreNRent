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

	
	
	public SettingsRepository(){
		
	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param setting
	 * @return 
	 */
	public PersonSettings createSettings(PersonSettings setting){
		EntityManager em = JPA.em();
		em.persist(setting);
		em.flush();
		return setting;

	}

	/**
	 * 
	 * @param settings
	 * @return 
	 */
	public PersonSettings updateSettings(PersonSettings settings){
		EntityManager em = JPA.em();
		em.merge(settings);
		em.flush();
		return settings;
	}

	/**
	 * 
	 * @param personID
	 */
	public PersonSettings findSettingsByPersonID(Person person){
		EntityManager em = JPA.em();
		List<PersonSettings> tmp = em.createNativeQuery(
				"SELECT * FROM PERSON_SETTINGS c WHERE c.person_id = ?", PersonSettings.class)
				.setParameter(1, person.id)
				.setMaxResults(10)
				.getResultList();
		if (tmp != null) {
			return (PersonSettings) tmp.get(0);
		} else {
			return null;
		}
	}

}