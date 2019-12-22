package fgarzag.ImageProcessor.service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

/*
 * This interface provides a basic architecture that multiple Service classes can implement
 * For now we only have one Service class (ImageServiceImpl)
 */
public interface ImageService {
	
	public void validateSourceFile(String path) throws FileNotFoundException;
	
	public void validateDestination(String path) throws FileNotFoundException;
	
	public void validateURL(String url) throws MalformedURLException;
	
	public String getImageName(String url);
	
	public String getImageType(String url) throws Exception;
	
}
