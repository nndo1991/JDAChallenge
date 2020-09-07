package fgarzag.ImageProcessor.repository;

import fgarzag.ImageProcessor.model.Image;

/*
 * This interface provides a basic architecture that multiple Repository classes can implement
 * For now we only have one Repository class (ImageWebRepositoryImpl) that reads images 
 * from the internet
 */
public interface ImageRepository {
	
	public Image getImage(String id) throws Exception;
	
	public void storeImage(Image image) throws Exception;
	
}
