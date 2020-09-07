package fgarzag.ImageProcessor;

import fgarzag.ImageProcessor.service.ImageServiceImpl;

public class MainApp 
{
    public static void main( String[] args )
    {
		/** Adjust parameters:
		 * parameter 1 is the source file for the URLs
		 * parameter 2 is the destination path were images will be stored
		 */
    	ImageServiceImpl imgservice = new ImageServiceImpl();
    	
    	if (args.length == 2) {
    		imgservice.processImages(args[0],
		 				 			 args[1]);
    	} else {
        	imgservice.processImages("C:/Users/fgarzag/Desktop/test/URLs_2.txt",
	   				 				 "C:/Users/fgarzag/Desktop/test/");
    	}
        
    }
}
