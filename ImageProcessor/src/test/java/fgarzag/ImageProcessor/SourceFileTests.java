package fgarzag.ImageProcessor;

import static org.junit.Assert.*;
import org.junit.Test;
import fgarzag.ImageProcessor.service.ImageServiceImpl;

public class SourceFileTests {

	@Test(expected = NullPointerException.class)
	public void NullFilePath() {
		ImageServiceImpl obj = new ImageServiceImpl();
		obj.validateSourceFile(null);
	}
	
	@Test
	public void getImageName() {
		ImageServiceImpl obj = new ImageServiceImpl();
		String name = obj.getImageName("http://is4mobi.di.unito.it/~schifane/sociopixels/animals/73WBA210.jpg");
		assertEquals("73WBA210.jpg",name);
		
	}

}
