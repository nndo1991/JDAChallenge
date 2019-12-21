package fgarzag.ImageProcessor.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import fgarzag.ImageProcessor.repository.ImageWebRepositoryImpl;

public class ImageServiceImpl implements ImageService{
	
	public ImageServiceImpl() {
		
	}
	
	public void validateSourceFile(String path) { 
		try {
			File newfile = new File(path);
			if (newfile.length() == 0) {
				System.out.println("The provided URLs file is empty");
			}
		} catch (Exception e) {
			System.out.println("The provided URLs file is invalid or does not exist");
			System.exit(0);
		}
	}
	
	public void validateDestination(String path) {
		try {
			File newfile = new File(path);
		} catch (Exception e) {
			System.out.println("The provided destination path is invalid or does not exist");
			System.exit(0);
		}
	}

	public void validateURL(String url) {
		try {
			URL obj = new URL(url);
			obj.toURI();
		} catch(MalformedURLException e) {
			System.out.println("ERROR: the source file contains an invalid URL:");
			System.out.println(url);
			System.exit(0);
		} catch(URISyntaxException e) {
			System.out.println("ERROR: the source file contains an invalid URL:");
			System.out.println(url);
			System.exit(0);
		}
	}

	public String getImageName(String url) {
		String name = url.substring(url.lastIndexOf('/')+1);
		return name;
	}

	public String getImageType(String url) {
		String type = url.substring(url.lastIndexOf('.')+1);
		return type;
	}
	
	public void processImages(String file_path, String destination_path){
        
        long start=System.currentTimeMillis();
        
        validateSourceFile(file_path);
        validateDestination(destination_path);
        
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file_path));
		} catch (FileNotFoundException e1) {
			System.out.println("Exception: Could not find file:");
			System.out.println(file_path);
			System.exit(0);
		}
		ExecutorService service = Executors.newFixedThreadPool(10);
		String line = null;
		ImageWebRepositoryImpl.destination_path = destination_path;
		
		try {
			while((line = reader.readLine()) != null) {
				validateURL(line);
				service.execute(new ImageWebRepositoryImpl(getImageName(line),line,getImageType(line)));
			}
		} catch (IOException e1) {
			System.out.println("Exception: Error while reading line:");
			System.out.println(line);
			System.exit(0);
		}
		
		service.shutdown();
		try {
			service.awaitTermination(2, TimeUnit.MINUTES);
            if (service.isTerminated()) {
            	System.out.println("Program finished after: "+ (System.currentTimeMillis()-start)/1000 + " seconds");
            } else {
            	service.shutdownNow();
            	System.out.println("Program has reached the timeout of 2 minutes");
            }
        } catch (InterruptedException e) {
        	service.shutdownNow();
			System.out.println("Thread interrupted while waiting");
        }

	}

}
