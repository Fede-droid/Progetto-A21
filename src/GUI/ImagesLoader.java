package GUI;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagesLoader {
	
	
	BufferedImage image;
	public BufferedImage uploadImage(String position) {
		
		try {
			image = ImageIO.read(getClass().getResource(position));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return image;
	}

}
