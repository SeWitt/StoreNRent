package serviceImpl;

import models.Picture;
import repository.PictureRepository;
import service.PictureService;

/**
 * 
 * @author max
 *
 */
public class PictureServiceImpl implements PictureService{

	@Override
	public Picture findPictureByID(int id) {
		return PictureRepository.findPictureByID(id);
	}

}
