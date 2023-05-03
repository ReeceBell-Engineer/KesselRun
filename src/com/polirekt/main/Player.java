package com.polirekt.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Player extends GameObject implements EntityA {
	
	private double velX = 0;
	private double velY = 0;
	private Textures tex;
	Game game;
	Controller con;
	HUD h;
	SoundEffect se;

	
	public Player(double x, double y, Textures tex, Game game, Controller con, HUD h, SoundEffect se) {
		super(x,y);
		this.tex = tex;
		this.game = game;
		this.con = con;
		this.h = h;
		this.se = se;
		
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0) {
			x = 0;
		}
		if (x >= 640 - 32) {
			x = 640 - 32;
		}
		if (y <= 0) {
			y = 0;
		}
		if (y >= 480 -46) {
			y = 480 - 46;
		}

		for (int i = 0; i < game.eb.size(); i++) {
			EntityB tempEnt = game.eb.get(i);
			if (Physics.Collision(this, tempEnt)) {
			    se.playExplosion(game.explosionURL);
				con.removeEntity(tempEnt);
				h.setLives(h.getLives() - 1);
				game.setEnemyKilled(game.getEnemyKilled() + 1);
				game.setTotalEnemyKilled(game.getTotalEnemyKilled() + 1);
			}
		}
		for (int i = 0; i < game.ec.size(); i++) {
			EntityC tempEntC = game.ec.get(i);
			if (Physics.Collision(this, tempEntC)) {
				con.removeEntity(tempEntC);
				h.setLives(h.getLives() + 1);
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.player, (int) x, (int) y, null);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
