package com.polirekt.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class ExtraLife extends GameObject implements EntityC {

	private Game game;
	private Textures tex;
	private Controller con;
	
	public ExtraLife(double x, double y, Textures tex, Game game, Controller con ) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.con = con;
	}
	
	public void tick() {
		y += 2;

		if (y > (Game.HEIGHT * Game.SCALE)) {
			con.removeEntity(this);
		}
		
		
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.extraLife, (int) x, (int) y, null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
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
