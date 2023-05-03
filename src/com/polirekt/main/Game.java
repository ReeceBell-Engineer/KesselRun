package com.polirekt.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 320;
	public static final int HEIGHT = (WIDTH / 12) * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Kessel Run";
	
	private boolean isRunning = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage backgroundImg = null;
	
	private boolean isShooting = false;
	
	private int enemyCount = 5;
	public int enemyKilled = 0;
	public int totalEnemyKilled = 0;
	public int health = 100 * 2;
	public boolean isPlaying = false;
	
	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;
	private GameOver go;
	private HUD h;
	private HighScores hs;
	
	
	SoundEffect se = new SoundEffect();
	BackgroundMu bm = new BackgroundMu();
	
	URL backgroundSoundURL, laserURL, explosionURL;
	
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	public LinkedList<EntityC> ec;
	
	
	
	public static enum STATE {
		MENU,
		GAME,
		HIGHSCORES,
		GAMEOVER
	};
	
	public static STATE state = STATE.MENU;

	public void init() throws IOException {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		spriteSheet = loader.loadImage("sprite_sheet.png");
		backgroundImg = loader.loadImage("background.png");
		
		tex = new Textures(this);
		
		c = new Controller(tex, this);
		h = new HUD(tex, this);
		hs = new HighScores();
		p = new Player(WIDTH - 16, (HEIGHT * SCALE) - 64, tex, this, c, h, se);
		menu = new Menu();
		ea = c.getEntityA();
		eb = c.getEntityB();
		ec = c.getEntityC();
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		
		
		
		backgroundSoundURL = getClass().getResource("/sounds/background1.wav");
		laserURL = getClass().getResource("/sounds/laser_shot.wav");
		explosionURL = getClass().getResource("/sounds/explosion1.wav");
		
		bm.playMusic(backgroundSoundURL);
//		bm.play();

	}
	
	public void newGame() {
		this.enemyCount = 5;
		this.enemyKilled = 0;
		this.totalEnemyKilled = 0;
		h.setWave(1);
		p.setX(WIDTH - 16);
		p.setY((HEIGHT * SCALE) - 64);
		h.lives = 3;
		if (ea != null) {
			ea.clear();
		}
		if (eb != null) {
			eb.clear();
		}
		if (ec != null) {
			ec.clear();
		}
		c.createEnemy(enemyCount);
	}
	
	private synchronized void start() {
		if(isRunning) {
			return;
		}
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if (!isRunning) {
			return;
		}
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	@Override
	public void run() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
//				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		
		if (state == STATE.GAME) {
//			System.out.println(state +", " + isPlaying);
			if(!isPlaying) {
				newGame();
				bm.playMusic(backgroundSoundURL);
				bm.play();
				isPlaying = true;
			}
			if (h.getLives() <= 0) {

				state = STATE.GAMEOVER;
				isPlaying = false;
				bm.stop();
				
			}
			
			if (totalEnemyKilled > 0 && totalEnemyKilled % 50 == 0 && h.getLives() < 4) {
				c.createExtraLife(totalEnemyKilled);
			}

			p.tick();
			c.tick();
			h.tick();
			
			if (enemyKilled >= enemyCount) {
				enemyCount += 2;
				enemyKilled = 0;
				h.setWave(h.getWave() + 1);
				c.createEnemy(enemyCount);
			}

		} else {
			
		}

		checkState();
	}
	
	private void checkState() {
		if (state == STATE.GAMEOVER) {
			go = new GameOver(this, h);
			
		}
		if (state == STATE.MENU) {
//			c.createEnemy(enemyCount);
		}
	}
	

	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		////////////////////////////////////////////////////////////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
//		g.drawImage(backgroundImg, 0, 0, null);
		
		if (state == STATE.GAME) {
			h.render(g);
			p.render(g);
			c.render(g);
			

		} else if (state == STATE.MENU) {
			menu.render(g);
		} else if (state == STATE.HIGHSCORES) {
			hs.render(g);
		} else if (state == STATE.GAMEOVER) {
			go.render(g);
		}
		////////////////////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}
	

	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			p.setVelX(5);
		} else if(key == KeyEvent.VK_LEFT) {
			p.setVelX(-5);
		} else if(key == KeyEvent.VK_UP) {
			p.setVelY(-5);
		} else if(key == KeyEvent.VK_DOWN) {
			p.setVelY(5);
		} else if(key == KeyEvent.VK_SPACE && !isShooting) {
			isShooting = true;
			c.addEntity(new Bullet(p.getX() + 14, p.getY(), tex, this, c));
			se.playLaser(laserURL);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		} else if(key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		} else if(key == KeyEvent.VK_UP) {
			p.setVelY(0);
		} else if(key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		} else if(key == KeyEvent.VK_SPACE) {
			isShooting = false;
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Image icon = Toolkit.getDefaultToolkit().getImage("./res/images/spaceship.png");  
		frame.setIconImage(icon);  
		frame.setVisible(true);
		
		game.start();
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	public BufferedImage getBackgroundImg() {
		return backgroundImg;
	}
	
	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public int getEnemyKilled() {
		return enemyKilled;
	}

	public void setEnemyKilled(int enemyKilled) {
		this.enemyKilled = enemyKilled;
	}

	public int getTotalEnemyKilled() {
		return totalEnemyKilled;
	}

	public void setTotalEnemyKilled(int totalEnemyKilled) {
		this.totalEnemyKilled = totalEnemyKilled;
	}
}
