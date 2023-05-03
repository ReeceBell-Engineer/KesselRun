package com.polirekt.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements EntityA {
	
	private Textures tex;
	private Controller con;
	private Game game;
	
	public Bullet(double x, double y, Textures tex, Game game, Controller con) {
		super(x, y);
		this.game = game;
		this.tex = tex;
		this.con = con;
	}
	
	public void tick() {
		y -= 10;
		
		for (int i = 0; i < game.ea.size(); i++) {
			EntityA tempEnt = game.ea.get(i);
			if (tempEnt.getY() < 0) {
				con.removeEntity(this);
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.bullet, (int) x, (int) y, null);
	}
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 4, 8);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
