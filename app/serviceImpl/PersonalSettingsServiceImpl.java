package serviceImpl;
import models.Person;
import models.PersonSettings;
import repository.SettingsRepository;
import service.PersonalSettingsService;

/**
 * @author Sebastian
 * @version 1.0
 * @created 23-Mai-2014 16:53:24
 */
public class PersonalSettingsServiceImpl implements PersonalSettingsService {

	private SettingsRepository personalSettingsRepo =  new SettingsRepository();

	public PersonalSettingsServiceImpl(){

	}

	@Override
	public PersonSettings createSettings(PersonSettings settings) {
		settings.isActive = true;
		return personalSettingsRepo.createSettings(settings);
	}

	@Override
	public PersonSettings updatePersonalSettings(PersonSettings settings) {
	
		return personalSettingsRepo.updateSettings(settings);
	}

	@Override
	public void deletePersonalSettings(PersonSettings settings) {
		settings.isActive = false;
		personalSettingsRepo.updateSettings(settings);
		
	}

	@Override
	public PersonSettings findSettingsByID(Person person) {
		return personalSettingsRepo.findSettingsByPersonID(person);
	}

	


}