package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Star;

public class UI {
	
	GamePanel gp;
	Font arial_40;
	BufferedImage starimage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial",Font.PLAIN,40);
		OBJ_Star star = new OBJ_Star();
		starimage = star.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
public void draw(Graphics2D g2) {
	
	g2.setFont(arial_40);
	g2.setColor(Color.white);
	g2.drawImage(starimage,20,7,50,50,null);
	g2.drawString("x "+gp.player.hasStar, 70, 50);
	
	if(messageOn == true) {
		g2.setFont(g2.getFont().deriveFont(30F));
		g2.drawString(message, 25, 50*5);
		
		messageCounter++;
		
		if(messageCounter>100) {
			messageCounter = 0;
			messageOn = false;
		}
	}
}
}
