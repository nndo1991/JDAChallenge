package fgarzag.ImageProcessor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App 
{
    public static void main( String[] args )
    {
		
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("src/main/resources/URLs_2.txt"));
		} catch (FileNotFoundException e1) {
			System.out.println("URL file could not be found");
			System.exit(0);
		}
        
		String line;
		ExecutorService service = Executors.newFixedThreadPool(10);
		
        try {
			while((line = reader.readLine()) != null) {
				//System.out.println(line);
				String image_name = line.substring(line.lastIndexOf('/')+1);
				//System.out.println(image_name);
				
				service.execute(new ImageDownloader(line,image_name));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
