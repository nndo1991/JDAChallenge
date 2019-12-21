package fgarzag.ImageProcessor.service;

public interface ImageService {
	
	public void validateSourceFile(String path);
	
	public void validateDestination(String path);
	
	public void validateURL(String url);
	
	public String getImageName(String url);
	
	public String getImageType(String url);
	
}
