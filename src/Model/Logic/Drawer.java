package Model.Logic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import GUI.ImagesLoader;
import Model.Core.Screen;
import Model.Items.ScreenItem;

public class Drawer {
	
	// Classe che gestisce il render effettivo degli oggetti sullo schermo di gioco 
	
	Graphics graphics;
	BufferStrategy buffer;
	
	public Drawer() {
	}
	
	// l'oggetto passato verrà disegnato
	public void draw(ScreenItem item) {
		
		int[] position = item.getPosition();
		int imageWidth = item.getImageWidth();
		int imageHeight = item.getImageHeight();
		BufferedImage image = item.getImage();
		graphics.drawImage(image, position[0], position[1], imageWidth, imageHeight, null);
	}
	
	public void draw(String string, int x, int y) {
		
		graphics.drawString(string, x, y);
	}
	
	
	// inzializzazione drawer 
	public void loadGraphics(Graphics graphics) {
		
		graphics.setFont(new Font("Courier", Font.BOLD, 20)); 
		graphics.setColor(Color.WHITE);
		this.graphics = graphics;
	}
}
