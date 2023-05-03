package com.polirekt.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MouseInput implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (Game.state == Game.STATE.MENU) {
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 150 && my <= 180) {
					Game.state = Game.STATE.GAME;
				}
			}
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 200 && my <= 230) {
					
				}
			}
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 250 && my <= 280) {
					Game.state = Game.STATE.HIGHSCORES;
					Scores score = new Scores();
				}
			}
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 300 && my <= 330) {
					System.exit(1);
				}
			}
		}
		if (Game.state == Game.STATE.GAMEOVER) {
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 150 && my <= 200) {
					Game.state = Game.STATE.MENU;
				}
			}
			if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 250 && my <= 300) {
					System.exit(1);
				}
			}
		}
		if (Game.state == Game.STATE.HIGHSCORES) {
			if (mx >= Game.WIDTH / 2 + 100 && mx <= Game.WIDTH / 2 + 220) {
				if (my >= 150 && my <= 180) {
					Game.state = Game.STATE.MENU;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
