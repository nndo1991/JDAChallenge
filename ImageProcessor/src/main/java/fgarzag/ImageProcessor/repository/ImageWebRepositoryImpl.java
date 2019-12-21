package fgarzag.ImageProcessor.repository;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import fgarzag.ImageProcessor.model.Image;

public class ImageWebRepositoryImpl implements ImageRepository, Runnable {
	
	private Image image;
	
	public static String destination_path;
	
	public ImageWebRepositoryImpl(String name, String url, String type) {
		this.image = new Image(name, url, type, null);
	}

	public Image getImage(String id) {
		BufferedImage bf = null;
		try {
			URL download_url = new URL(id);
			bf = ImageIO.read(download_url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		image.setContent(bf);
		return image;
	}

	public void storeImage(Image image) {
		
		try {
			ImageIO.write(image.getContent(), image.getType(), new File(destination_path+image.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		
		System.out.println("Downloading image: "+image.getName()+" from thread: "+Thread.currentThread().getName());
		image = getImage(image.getUrl());
		storeImage(image);
		
	}

}
