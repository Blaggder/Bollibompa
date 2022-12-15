package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];

	public void Soundsound(String s, int i) {
		soundURL[i] = getClass().getResource("/sounds/" + s + ".wav");
	}

	public void Soundmusic(String s, int i) {
		soundURL[i] = getClass().getResource("/music/" + s + ".wav");
	}

	public Sound() {
		Soundsound("1202_S_stjarna", 0);
		Soundsound("1203_S_hotspot", 2);
		Soundmusic("1190_S_bg1", 1);
		Soundsound("5_draken_info", 3);
	}

	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
		}
	}

	public void play() {
		clip.start();

	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}

	public void volumes(float f) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(f); // Reduce volume by 10 decibels.

	}
}
