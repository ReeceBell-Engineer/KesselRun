package com.polirekt.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class HighScores {
	
	public Rectangle mainMenuBtn = new Rectangle(Game.WIDTH / 2 + 100, 150, 120,30);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font0 = new Font("arial", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("Kessel Run", Game.WIDTH / 2, 100);
		
		Font font1 = new Font("arial", Font.BOLD, 20);
		g.setFont(font1);
		g.drawString("Main Menu", mainMenuBtn.x + 10, mainMenuBtn.y + 25);
		g2d.draw(mainMenuBtn);
		
	}
}
