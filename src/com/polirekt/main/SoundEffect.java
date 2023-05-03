package com.polirekt.main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
	
	Clip clip;
	
	public void playLaser(URL laser) {
	    try{

	    	AudioInputStream sound = AudioSystem.getAudioInputStream(laser);
	        clip = AudioSystem.getClip();
	        clip.open(sound);
	        play();

	    } catch (Exception ee){
	        ee.printStackTrace();
	    }
	}
	
	public void playExplosion(URL explosion) {
	    try{

	    	AudioInputStream sound = AudioSystem.getAudioInputStream(explosion);
	        clip = AudioSystem.getClip();
	        clip.open(sound);
	        play();
	    } catch (Exception ee){
	        ee.printStackTrace();
	    }
	}
	
	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}

}
