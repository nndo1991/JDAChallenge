package fgarzag.ImageProcessor;

import static org.junit.Assert.*;
import org.junit.Test;
import fgarzag.ImageProcessor.service.ImageServiceImpl;
import java.io.FileNotFoundException;

public class ServiceTests {
	
	// Test with input file as NULL
	@Test(expected = FileNotFoundException.class)
	public void nullFilePath() throws FileNotFoundException {
		ImageServiceImpl obj = new ImageServiceImpl();
		obj.validateSourceFile(null);
	}
	
	//Test with an empty input file
	@Test(expected = FileNotFoundException.class)
	public void emptyFile() throws FileNotFoundException {
		ImageServiceImpl obj = new ImageServiceImpl();
		obj.validateSourceFile("C:/Users/fgarzag/Desktop/test/URLs_5.txt");
	}
	
	//Test with a non existent file
	@Test(expected = FileNotFoundException.class)
	public void notExistentFile() throws FileNotFoundException {
		ImageServiceImpl obj = new ImageServiceImpl();
		obj.validateSourceFile("C:/Users/fgarzag/Desktop/test/notexists.txt");
	}
	
	//Test with null Destination Path
	@Test(expected = FileNotFoundException.class)
	public void nullDestination() throws FileNotFoundException {
		ImageServiceImpl obj = new ImageServiceImpl();
		obj.validateDestination(null);
	}
	
	//Test with a Destination Path that does not exist in file system
	@Test(expected = FileNotFoundException.class)
	public void wrongDestination() throws FileNotFoundException {
		ImageServiceImpl obj = new ImageServiceImpl();
		obj.validateDestination("C:/Users/invalid/not/exists/");
	}
	
	//Test with a correct image extension
	@Test(expected = Exception.class)
	public void wrongExtension() throws Exception {
		ImageServiceImpl obj = new ImageServiceImpl();
		obj.getImageType("http://url.com/image.pdf");
	}
	
	//Test with a correct image extension
	@Test
	public void rightExtension() throws Exception {
		ImageServiceImpl obj = new ImageServiceImpl();
		String type = obj.getImageType("http://url.com/image.JPG");
		assertEquals("JPG",type);
	}
	
	//Test with a correct image name
	@Test
	public void getImageName() {
		ImageServiceImpl obj = new ImageServiceImpl();
		String name = obj.getImageName("http://url.com/73WBA210.jpg");
		assertEquals("73WBA210.jpg",name);
		
	}

}
