
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = maxWorldCol * 50;
	public final int worldHeight = maxWorldRow * 50;
	public String map = "map4";

	int FPS = 60;

	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	AssetSetter aSet = new AssetSetter(this);
	Sound music = new Sound();
	Sound sound = new Sound();
	public UI ui = new UI(this);
	public Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[20];

	public GamePanel() {

		this.setPreferredSize(new Dimension(1000, 500));
		this.setBackground(new Color(255, 192, 203));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void setupGame() {
		aSetter.setObject();

		playMusic(1);
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();

	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;

			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}

		}

	}

	public void update() {

		player.update();

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		tileM.draw(g2);

		// tileM.draw2(g2);
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}

		player.draw(g2);

		ui.draw(g2);

		g2.dispose();
	}

	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}

	public void stopMusic() {
		music.stop();
	}

	public void volumes(float f) {
		music.volumes(f);
	}
//	public void pauseMusic(int i) {
//		music.stop();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			playMusic(i);;
//		
//		
//		
//	}

	public void playSound(int i) {
		sound.setFile(i);
		sound.play();
	}

	public void mapChange() {
		tileM.loadMap(map);
		aSet.setObject();
	}

}
