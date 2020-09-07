package fgarzag.ImageProcessor.model;

import java.awt.image.BufferedImage;

/*This model if the basic definition of an Image object for our Program
 *For now we only need the name, url, type, and the actual content of the image
 *but it can easily be expanded to contain more attributes and implemented by other clases
 */
public class Image {
	
	private String name;
	private String url;
	private String type;
	private BufferedImage content;
	
	public Image(String name, String url, String type, BufferedImage content) {
		this.name = name;
		this.url = url;
		this.type = type;
		this.content = content;
	}
	
	public Image(){
		this.name = null;
		this.url = null;
		this.type = null;
		this.content = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public BufferedImage getContent() {
		return content;
	}

	public void setContent(BufferedImage content) {
		this.content = content;
	}
	
}
