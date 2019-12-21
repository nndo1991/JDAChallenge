package fgarzag.ImageProcessor.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import fgarzag.ImageProcessor.repository.ImageWebRepository;

public class ImageService {
	
	public ImageService() {
		
	}
	
	public static void processImages(String file_path) {
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file_path));
		} catch (FileNotFoundException e1) {
			System.out.println("URL file could not be found");
			System.exit(0);
		}
		
		String line;
		ExecutorService service = Executors.newFixedThreadPool(10);
		
        try {
			while((line = reader.readLine()) != null) {
				//System.out.println(line);
				String name = line.substring(line.lastIndexOf('/')+1);
				String type = line.substring(line.lastIndexOf('.')+1);
				//System.out.println(image_name);
				
				service.execute(new ImageWebRepository(name,line,type));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        service.shutdown();
	}
}
