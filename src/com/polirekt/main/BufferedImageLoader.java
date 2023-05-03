package com.polirekt.main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource("/images/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
