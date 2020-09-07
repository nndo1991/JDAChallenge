package fgarzag.ImageProcessor;

import java.io.FileNotFoundException;
import org.junit.Test;
import fgarzag.ImageProcessor.model.Image;
import fgarzag.ImageProcessor.repository.ImageWebRepositoryImpl;

public class RepositoryTests {

	// Test with a valid image URL
	@Test
	public void correctImageURL() throws Exception {
		String name = "73WBA210.jpg";
		String url = "http://is4mobi.di.unito.it/~schifane/sociopixels/animals/73WBA210.jpg";
		String type = "jpg";
		ImageWebRepositoryImpl obj = new ImageWebRepositoryImpl(name, url, type);
		obj.getImage(url);
	}
	
	// Test with a valid image URL
	@Test(expected = Exception.class)
	public void incorrectImageURL() throws Exception {
		String name = "73WBA210.jpg";
		String url = "http://invalid.url/73WBA210.jpg";
		String type = "jpg";
		ImageWebRepositoryImpl obj = new ImageWebRepositoryImpl(name, url, type);
		obj.getImage(url);
	}
	
	/* TO RUN THIS TEST REPLACE LINE 36 WITH A VALID LOCAL PATH BEFORE THE BULDING THE APP
	@Test
	public void correctLocation() throws Exception {
		String name = "73WBA210.jpg";
		String url = "http://is4mobi.di.unito.it/~schifane/sociopixels/animals/73WBA210.jpg";
		String type = "jpg";
		ImageWebRepositoryImpl.destination_path = "C:/Users/fgarzag/Desktop/test/";
		ImageWebRepositoryImpl obj = new ImageWebRepositoryImpl(name, url, type);
		Image img = obj.getImage(url);
		obj.storeImage(img);
	}
	*/
	
	// Store image to an incorrect location
	@Test(expected = FileNotFoundException.class)
	public void incorrectLocation() throws Exception {
		String name = "73WBA210.jpg";
		String url = "http://is4mobi.di.unito.it/~schifane/sociopixels/animals/73WBA210.jpg";
		String type = "jpg";
		ImageWebRepositoryImpl.destination_path = "C:/does/not/exist/";
		ImageWebRepositoryImpl obj = new ImageWebRepositoryImpl(name, url, type);
		Image img = obj.getImage(url);
		obj.storeImage(img);
	}

}
