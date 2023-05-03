package com.polirekt.main;

import java.awt.Graphics;

public class Background extends GameObject {
	
	private Game game;
	
	private double speed = 1;
	
	public Background(double x, double y, Game game) {
		super(x, y);
		this.game = game;
	}
	
	public void tick() {
		y += speed;
	}
	
	public void render(Graphics g) {
		g.drawImage(game.getBackgroundImg(),(int) x, (int) y, null);
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
