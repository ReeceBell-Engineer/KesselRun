package com.polirekt.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Astroid2 extends GameObject implements EntityB {
	private double velX = 0;
	private double velY = 0;
	
	Random rand = new Random();
	
	private Game game;
	private Controller con;
	
	private int randInt = rand.nextInt(3) + 1;
	private double speed = rand.nextDouble() + randInt;
	private Textures tex;
	
	public Astroid2(double x, double y, Textures tex, Game game, Controller con) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.con = con;
	}
	
	public void tick() {
		y += speed;
		
		if (y > (Game.HEIGHT * Game.SCALE)) {
			speed = rand.nextDouble() + randInt;
			y = -32;
			x = 32 + rand.nextInt(Game.WIDTH * Game.SCALE) - 64;
		}
		for (int i = 0; i < game.ea.size(); i++) {
			EntityA tempEnt = game.ea.get(i);
			
			
			if (Physics.Collision(this, tempEnt)) {
				con.removeEntity(tempEnt);
				con.removeEntity(this);
				game.setEnemyKilled(game.getEnemyKilled() + 1);
				game.setTotalEnemyKilled(game.getTotalEnemyKilled() + 1);
			}
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.astroid2, (int) x, (int) y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 26, 26);
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
}
