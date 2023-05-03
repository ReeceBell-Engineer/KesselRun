package com.polirekt.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	
	private LinkedList<EntityA> ea = new LinkedList<>();
	private LinkedList<EntityB> eb = new LinkedList<>();
	private LinkedList<EntityC> ec = new LinkedList<>();
	
	EntityA entA;
	EntityB entB;
	EntityC entC;
	
	private int countEL = 0;
	private int tek;
	
	private Textures tex;
	Random r = new Random();
	
	private Game game;
	
	public Controller(Textures tex, Game game) {
		this.tex = tex;
		this.game = game;
	}
	
	public void createEnemy(int enemyCount) {
		int max = 2;
		int min = 1;
		for (int i = 0; i < enemyCount; i++) {
			int enemy = r.nextInt(max - min + 1) + min;
			if (enemy == 1) {
				addEntity(new Astroid(r.nextInt((Game.WIDTH * Game.SCALE) - 32),r.nextInt(300) * - 1,tex, game, this));
			} else if (enemy == 2) {
				addEntity(new Astroid2(r.nextInt((Game.WIDTH * Game.SCALE) - 200),r.nextInt(300) * - 1,tex, game, this));
			}
			
		}
	}
	
	public void createExtraLife(int tek) {
		if (tek > this.tek) {
			addEntity(new ExtraLife(r.nextInt((Game.WIDTH * Game.SCALE) - 32),r.nextInt(10),tex, game, this));
			this.tek = tek;
		}
	}
	
	
	public void tick() {
		for (int i = 0; i < ea.size(); i++) {
			entA = ea.get(i);
			entA.tick();
		}
		
		for (int i = 0; i < eb.size(); i++) {
			entB = eb.get(i);
			entB.tick();
		}
		for (int i = 0; i < ec.size(); i++) {
			entC = ec.get(i);
			entC.tick();
		}


	}
	
	public void render(Graphics g) {
		for (int i = 0; i < ea.size(); i++) {
			entA = ea.get(i);
			entA.render(g);
		}
		for (int i = 0; i < eb.size(); i++) {
			entB = eb.get(i);
			entB.render(g);
		}
		for (int i = 0; i < ec.size(); i++) {
			entC = ec.get(i);
			entC.render(g);
		}
	}
	
	public void addEntity(EntityA block) {
		ea.add(block);
	}
	public void removeEntity(EntityA block) {
		ea.remove(block);
	}
	public void addEntity(EntityB block) {
		eb.add(block);
	}
	public void removeEntity(EntityB block) {
		eb.remove(block);
	}
	public void addEntity(EntityC block) {
		ec.add(block);
	}
	public void removeEntity(EntityC block) {
		ec.remove(block);
	}
	public LinkedList<EntityA> getEntityA() {
		return ea;
	}
	public LinkedList<EntityB> getEntityB() {
		return eb;
	}
	public LinkedList<EntityC> getEntityC() {
		return ec;
	}
	
	public int getCountEL() {
		return countEL;
	}
	
	public void setCountEL(int countEL) {
		this.countEL = countEL;
	}
	
}
