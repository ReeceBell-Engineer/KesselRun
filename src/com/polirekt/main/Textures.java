package com.polirekt.main;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Textures {
	
	public BufferedImage player, astroid, astroid2, bullet, extraLife;
	private SpriteSheet ss;
			
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	public void getTextures() {
		Random rand = new Random();
		int max = 3;
		int min = 2;
		int col = rand.nextInt(max - min + 1) + min;
		player = ss.grabImage(1, 1, 32, 32);
		bullet = ss.grabImage(4, 1, 4, 8);
		astroid = ss.grabImage(1, 2, 32, 32);
		astroid2 = ss.grabImage(3, 1, 32, 32);
		extraLife = ss.grabImage(1, 1, 32, 32);
	}
}
