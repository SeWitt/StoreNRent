package serviceDummy;

import model.Person;
import model.PersonSettings;
import service.PersonalSettingsService;

public class PersonalSettingsServiceDummy implements PersonalSettingsService {

	
	@Override
	public PersonSettings createSettings(PersonSettings settings) {
		settings.isActive = true;
		return settings;
	}

	@Override
	public PersonSettings updatePersonalSettings(PersonSettings settings) {
	
		return settings;
	}

	@Override
	public void deletePersonalSettings(PersonSettings settings) {
		settings.isActive = false;
	}

	@Override
	public PersonSettings findSettingsByID(Person person) {
		PersonSettings p = new PersonSettings();
		p.displayFirstNameOnly = false;
		p.id = 42;
		p.isActive = person.isActive;
		p.sendNewsletter = true;
		p.person = person;
		
		return p;
	}

}
