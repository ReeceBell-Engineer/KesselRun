package com.polirekt.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playBtn = new Rectangle(Game.WIDTH / 2 + 120, 150, 100,30);
	public Rectangle helpBtn = new Rectangle(Game.WIDTH / 2 + 120, 200, 100,30);
	public Rectangle highScoresBtn = new Rectangle(Game.WIDTH / 2 + 120, 250, 100,30);
	public Rectangle quitBtn = new Rectangle(Game.WIDTH / 2 + 120, 300, 100,30);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font0 = new Font("arial", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("Kessel Run", Game.WIDTH / 2, 100);
		
		Font font1 = new Font("arial", Font.BOLD, 20);
		g.setFont(font1);
		g.drawString("Play", playBtn.x + 19, playBtn.y + 25);
		g2d.draw(playBtn);
		g.drawString("Help", helpBtn.x + 19, helpBtn.y + 25);
		g2d.draw(helpBtn);
		g.drawString("Scores", highScoresBtn.x + 19, highScoresBtn.y + 25);
		g2d.draw(highScoresBtn);
		g.drawString("Quit", quitBtn.x + 19, quitBtn.y + 25);
		g2d.draw(quitBtn);
		
	}
}
