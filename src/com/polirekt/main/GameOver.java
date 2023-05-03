package com.polirekt.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Scanner;

public class GameOver {

	public Rectangle menuBtn = new Rectangle(Game.WIDTH / 2 + 60, 150, 200,50);
	public Rectangle quitBtn = new Rectangle(Game.WIDTH / 2 + 120, 250, 100,50);
	
	Game game;
	HUD h;
	
	public String name;
	public int rounds;
	public int kills;
	
	public GameOver(Game game, HUD h) {
		this.game = game;
		this.h = h;
		
		Scores score = new Scores();
		Scanner scan = new Scanner(System.in);
		name = scan.next();
		scan.close();
		score.writeScores(name, h.getWave() - 1, game.getTotalEnemyKilled());
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font0 = new Font("arial", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("Kessel Run", Game.WIDTH / 2, 100);

		
		Font font1 = new Font("arial", Font.BOLD, 30);
		g.setFont(font1);
		g.drawString("Main Menu", menuBtn.x + 19, menuBtn.y + 35);
		g2d.draw(menuBtn);
		g.drawString("Quit", quitBtn.x + 19, quitBtn.y + 35);
		g2d.draw(quitBtn);
		
		g.drawString("You destroyed ", Game.WIDTH / 2 - 150, 250);
		g.drawString("Astroids", Game.WIDTH / 2 - 120, 315);
		g.drawString("and survived", Game.WIDTH / 2 - 150, 360);
		
		g.setColor(Color.RED);
		g.drawString(String.valueOf(game.getTotalEnemyKilled()), Game.WIDTH / 2 - 75, 285);
		g.drawString(String.valueOf(h.getWave() - 1) + " rounds!", Game.WIDTH / 2 - 125, 400);
	}
}
