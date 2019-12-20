package fgarzag.ImageProcessor;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class App 
{
    public static void main( String[] args )
    {
		
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("src/main/resources/URLs.txt"));
		} catch (FileNotFoundException e1) {
			System.out.println("URL file could not be found");
			System.exit(0);
		}
        
		String line;
        try {
			while((line = reader.readLine()) != null) {
				System.out.println(line);
				String image_name = line.substring(line.lastIndexOf('/')+1);
				System.out.println(image_name);
				
				BufferedImage image = null;
				URL url = new URL(line);
				image = ImageIO.read(url);
				
				ImageIO.write(image, "jpg", new File("src/main/resources/Images/"+image_name));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
