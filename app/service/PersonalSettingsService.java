package service;

import model.Person;
import model.PersonSettings;

public interface PersonalSettingsService {

	/**
	 * 
	 * @param settings
	 */
	public abstract PersonSettings createSettings(PersonSettings settings);

	/**
	 * 
	 * @param settings
	 */
	public abstract PersonSettings updatePersonalSettings(
			PersonSettings settings);

	/**
	 * 
	 * @param settings
	 */
	public abstract void deletePersonalSettings(PersonSettings settings);

	/**
	 * 
	 * @param personID
	 */
	public abstract PersonSettings findSettingsByID(Person person);

}