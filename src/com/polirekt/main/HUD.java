package com.polirekt.main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	public int lives = 3;
	private int totalPoints = 0;
	private int wave = 1;

	private Textures tex;
	Game game;
	Background bg;
	
	
	
	public HUD(Textures tex, Game game) {
		this.tex = tex;
		this.game = game;
		
		bg = new Background(0, -200, game);
	}
	
	public void tick() {
//		if (bg.getY() > -50) {
//			bg = new Background(0, -300, game);
//		}
		bg.tick();
	}
	
	public void render(Graphics g) {
		int x = 10;
		int y = 10;
		
		for (int i = 0; i < lives; i++) {
			g.drawImage(tex.player, x, y, null);
			x += 40;
		}
		
//		g.drawImage(game.getBackgroundImg(), 0, 0, null);
		bg.render(g);
		Font font1 = new Font("arial", Font.BOLD, 20);
		g.setFont(font1);
		
		g.setColor(Color.WHITE);
		g.drawString("Enemies Killed: ", Game.WIDTH / 2, 40);
		g.drawString("Wave: ", Game.WIDTH + 150, 40);
		
		g.setColor(Color.RED);
		g.drawString(String.valueOf(game.getTotalEnemyKilled()), Game.WIDTH / 2 + 175, 40);
		g.drawString(String.valueOf(wave), Game.WIDTH + 220, 40);
	}

	public int getLives() {
		return lives;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public int getWave() {
		return wave;
	}
	public void setWave(int wave) {
		this.wave = wave;
	}
}
