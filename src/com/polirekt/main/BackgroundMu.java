package com.polirekt.main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class BackgroundMu {
	Clip clip;
	
	public void playMusic(URL bgMu) {
	    try{

	    	AudioInputStream sound = AudioSystem.getAudioInputStream(bgMu);
	        clip = AudioSystem.getClip();
	        clip.open(sound);

	    } catch (Exception ee){
	        ee.printStackTrace();
	    }
	}
	
	public void play() {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
		gainControl.setValue(20f * (float) Math.log10(0.3));
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		clip.close();
	}
}
