package fgarzag.ImageProcessor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImageDownloader implements Runnable {
	
	private String url;
	private String image_name;
	
	public ImageDownloader(String url, String image_name) {
		this.url = url;
		this.image_name = image_name;
	}

	public void run() {
		
        try {
			
        	System.out.println("Downloading image: "+image_name+" from thread: "+Thread.currentThread().getName());
        	
        	BufferedImage image = null;
			URL download_url = new URL(url);
			image = ImageIO.read(download_url);
			ImageIO.write(image, "jpg", new File("src/main/resources/Images/"+image_name));
			
			System.out.println("Finished: "+image_name);
			
		} catch (IOException e) {
			System.out.println("--- ERROR FOR IMAGE: "+url);
			e.printStackTrace();
		}

	}
	
	
}
