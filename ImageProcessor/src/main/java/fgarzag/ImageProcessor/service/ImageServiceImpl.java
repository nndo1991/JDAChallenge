package fgarzag.ImageProcessor.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import fgarzag.ImageProcessor.repository.ImageWebRepositoryImpl;

public class ImageServiceImpl implements ImageService{
	
	/* Empty Constructor for the class */
	public ImageServiceImpl() {
		
	}
	
	/*
	 * This function validates the source text file from which the URLs will be read
	 * If the file was not provided, is empty, or cannot be read from the file
	 * system, the function will throw an Exception and stop the whole Program
	 */
	public void validateSourceFile(String path) throws FileNotFoundException { 
		if(path == null) {
			throw new FileNotFoundException("ERROR: no URL file was provided as parameter");
		}
		File newfile = new File(path);
		if (newfile.length() == 0) {
			throw new FileNotFoundException("ERROR: The provided URL file does not exist or is empty");
		}
		
	}
	
	/*
	 * This function validates the destination path on the file system where images
	 * will be stored is the path was not provided or does not exist in the file
	 * system, this function will throw an Exception and stop the whole Program
	 */
	public void validateDestination(String path) throws FileNotFoundException{
		if(path == null) {
			throw new FileNotFoundException("ERROR: Destination path to save images was not provided");
		}
		File newfile = new File(path);
		if (!newfile.exists() || !newfile.isDirectory()) {
			throw new FileNotFoundException("ERROR: The provided Destination path does not exist or is not a directory");
		}
	}

	/*
	 * This function validates a URL received as parameter if the URL is incorrect
	 * or incomplete, an Exception will be thrown
	 */
	public void validateURL(String url) throws MalformedURLException {
		try {
			URL obj = new URL(url);
			obj.toURI();
		} catch(Exception e) {
			throw new MalformedURLException("ERROR: the source file contains an invalid URL: \n"+url+"\n");
		}
	}
	
	/*
	 * This function receives a whole URL and extracts the file name and extension
	 * at the end to use it later on when storing the image locally Example: for
	 * http://url.com/pic.jpg this function will only return pic.jpg
	 */	public String getImageName(String url) {
		String name = url.substring(url.lastIndexOf('/')+1);
		return name;
	}
	 
	/*
	 * This function receives a whole URL and extracts the extension of the file if
	 * the file has no extension or is not a supported image type jpg/gif/png/jpeg
	 * the function will throw an Exception
	 */
	public String getImageType(String url) throws Exception {
		String type = url.substring(url.lastIndexOf('.')+1);
		if (!type.matches("jpg|gif|png|jpeg|JPG|GIF|PNG|JPEG")) {
			throw new Exception("Invalid image extension in line: \n"+url+"\n Supported are JPG, JPEG, GIF, PNG");
		}
		return type;
	}
	
	/*
	 * This is the main function that will be executed from an external source like
	 * the MainApp.java It contains all the business logic to validate, read source
	 * file, and invoke the Respository class for each URL in the source file. It
	 * also uses a Thread approach to handle these tasks concurrently
	 */
	public void processImages(String file_path, String destination_path){
        
		try {
			long start=System.currentTimeMillis();
	        
			// Validate source URL file and destination path to store the resulting images
	        validateSourceFile(file_path);
	        validateDestination(destination_path);
	        
	        //Start a new buffered reader to read source file and create a pool of 10 Threads
	        BufferedReader reader = new BufferedReader(new FileReader(file_path));
			ExecutorService service = Executors.newFixedThreadPool(10);
			String line = null;
			
			// destination path in the file system will be the same for all images,
			// no need to set it for each instance
			ImageWebRepositoryImpl.destination_path = destination_path;
			
			//For each URL in the file, we need to validate it and then submit it to the concurrent Thread service
			//If a specific URL contains a problem, we alert it and move to the next one
			while((line = reader.readLine()) != null) {
				try {
					validateURL(line);
					service.execute(new ImageWebRepositoryImpl(getImageName(line),line,getImageType(line)));
				} catch (Exception e) {
					System.out.println();
					System.out.println("----------------------------------------------");
					System.out.println("IMAGE ERROR:");
					System.out.println(e.toString());
					System.out.println("----------------------------------------------");
					System.out.println();
				}
			}
	        
			// After all URLs are processed, shutdown the Thread service
			// Set a timeout of 2 minutes to the whole Program, terminate it if it's been exceeded
			service.shutdown();
			service.awaitTermination(2, TimeUnit.MINUTES);
            if (service.isTerminated()) {
            	System.out.println("Program finished after: "+ (System.currentTimeMillis()-start)/1000 + " seconds");
            } else {
            	service.shutdownNow();
            	System.out.println("STOPPING PROGRAM: the timeout of 2 minutes has been reached");
            }
            
            // close the buffered reader
            reader.close();
			
		} catch (Exception e) {
			// If there are any major exceptions, terminate the whole Program
			System.out.println();
			System.out.println("----------------------------------------------");
			System.out.println("STOPPING PROGRAM EXECUTION, EXCEPTION FOUND: ");
			System.out.println("----------------------------------------------");
			System.out.println(e.toString());
			System.exit(0);
		}

	}

}
