package serviceImpl;
import model.PersonSettings;
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
	public PersonSettings createSettings(model.PersonSettings settings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonSettings updatePersonalSettings(
			model.PersonSettings settings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePersonalSettings(PersonSettings settings) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PersonSettings findSettingsByID(long personID) {
		// TODO Auto-generated method stub
		return null;
	}


}