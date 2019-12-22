package fgarzag.ImageProcessor.repository;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import fgarzag.ImageProcessor.model.Image;

public class ImageWebRepositoryImpl implements ImageRepository, Runnable {
	
	private Image image;
	
	/* This variable is static since it will be the same for all instances */
	public static String destination_path;
	
	/*
	 * Constructor for the Repository class, it creates a new Image
	 */
	public ImageWebRepositoryImpl(String name, String url, String type) {
		this.image = new Image(name, url, type, null);
	}
	
	/*
	 * This function gets a URL and gets it from there If the URL is incorrect or
	 * does not have an image, it throws an Exception.
	 * Else it returns the fetched image as an Image object
	 */
	public Image getImage(String id) throws Exception {
		BufferedImage bf = null;
		try {
			URL download_url = new URL(id);
			bf = ImageIO.read(download_url);
		} catch (IOException e) {
			throw new IOException("ERROR: Could not retrieve image from URL: \n"+id);
		}
		image.setContent(bf);
		return image;
	}
	
	/*
	 * This function receives an Image object and attempts to save it to the static
	 * destination path previously set to the class. If the path is invalid or there
	 * is an error during the write operation, an Exception is thrown
	 */
	public void storeImage(Image image) throws Exception {
		
		File directory = new File(destination_path);
		if (!directory.exists() || !directory.isDirectory()) {
			throw new FileNotFoundException("ERROR: The provided Destination path does not exist or is not a directory");
		}
		
		File f = new File(destination_path+image.getName());
		try {
			ImageIO.write(image.getContent(), image.getType(), f);
		} catch (IOException e) {
			throw new IOException("ERROR: Could not store image: "+image.getName());
		}
		
	}
	
	/*
	 * This function is called concurrently by the service.execute call of the
	 * Service class For each URL received, it will try to get it and store it to
	 * the destination path Each URL/instance is independent from eachother, if one
	 * image is unable to be fetched or stored, the other ones will work normally
	 */
	public void run() {
		
		try {
			image = getImage(image.getUrl());
			System.out.println("Downloadaded image: "+image.getName()+" from thread: "+Thread.currentThread().getName());
			storeImage(image);
		} catch (Exception e) {
			System.out.println();
			System.out.println("----------------------------------------------");
			System.out.println("EXCEPTION WHILE TRYING TO READ/STORE IMAGE");
			System.out.println(e.toString());
			System.out.println("----------------------------------------------");
			System.out.println();
		}
		
	}

}
