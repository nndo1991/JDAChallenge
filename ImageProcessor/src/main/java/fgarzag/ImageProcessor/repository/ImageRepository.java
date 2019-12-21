package fgarzag.ImageProcessor.repository;

import fgarzag.ImageProcessor.model.Image;

public interface ImageRepository {
	
	public Image getImage(String id);
	
	public void storeImage(Image image);
	
}
