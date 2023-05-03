package com.polirekt.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Astroid extends GameObject implements EntityB {
	
	Random rand = new Random();
	
	private Game game;
	private Controller con;
	
	private int randInt = rand.nextInt(3) + 1;
	private double speed = rand.nextDouble() + randInt;
	private Textures tex;
	
	public Astroid(double x, double y, Textures tex, Game game, Controller con) {
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
		g.drawImage(tex.astroid, (int) x, (int) y, null);
	}
	
	// hitbox
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 26, 27);
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
}
