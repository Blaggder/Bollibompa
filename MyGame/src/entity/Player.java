package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	public final int screenY;
	public final int screenX;
	public int hasStar = 0;
	public int soundCounter = 0;
	public boolean soundOn = false;

	public Player(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;

		screenX = (1000 / 2) - 25;
		screenY = (500 / 2) - 25;

		solidArea = new Rectangle();
		solidArea.x = 15;
		solidArea.y = 35;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.height = 15;
		solidArea.width = 20;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {

		worldX = 50 * 10;
		worldY = 50 * 20;
		speed = 10;
		direction = "down";
	}

	public void getPlayerImage() {

		try {

			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {

		if (soundOn) {
			soundCounter++;
			if (soundCounter > 300) {
				gp.volumes(+6f);
			}
			if (soundCounter > 350) {
				gp.volumes(+6f);
			}
			if (soundCounter > 400) {
				gp.volumes(+6f);
				soundOn = false;
			}
		}

		if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed) {
			spriteCounter++;
			collisionOn = false;
			Xaxis = false;
			Yaxis = false;
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			gp.cChecker.checkTile(this);

			if (collisionOn) {
				switch (direction) {
				case "up":
					worldY += speed;
					break;
				case "rightUp":
					if (Xaxis && Yaxis) {
						worldY += speed;
						worldX -= speed;
					} else if (Xaxis) {
						worldX -= speed;
					} else if (Yaxis) {
						worldY += speed;
					} else {
						worldY += speed;
						worldX -= speed;
					}

					break;
				case "leftUp":
					if (Xaxis && Yaxis) {
						worldY += speed;
						worldX += speed;
					} else if (Xaxis) {
						worldX += speed;
					} else if (Yaxis) {
						worldY += speed;
					} else {
						worldY += speed;
						worldX += speed;
					}
					break;
				case "down":
					worldY -= speed;
					break;
				case "rightDown":
					if (Xaxis && Yaxis) {
						worldY -= speed;
						worldX -= speed;
					} else if (Xaxis) {
						worldX -= speed;
					} else if (Yaxis) {
						worldY -= speed;
					} else {
						worldY -= speed;
						worldX -= speed;
					}
					break;
				case "leftDown":
					if (Xaxis && Yaxis) {
						worldX += speed;
						worldY -= speed;
					} else if (Xaxis) {
						worldX += speed;
					} else if (Yaxis) {
						worldY -= speed;
					} else {
						worldX += speed;
						worldY -= speed;
					}
					break;
				case "left":
					worldX += speed;
					break;
				case "right":
					worldX -= speed;
					break;
				}
			}
		}

		if (keyH.upPressed == true) {
			if (keyH.rightPressed && keyH.leftPressed) {
				direction = "up";
				worldY -= speed;
			} else if (keyH.rightPressed) {
				direction = "rightUp";
				worldY -= speed;
				worldX += speed;
			} else if (keyH.leftPressed) {
				direction = "leftUp";
				worldY -= speed;
				worldX -= speed;
			} else {
				direction = "up";
				worldY -= speed;
			}
		} else if (keyH.downPressed == true) {
			if (keyH.rightPressed && keyH.leftPressed) {
				direction = "down";
				worldY += speed;
			} else if (keyH.rightPressed) {
				direction = "rightDown";
				worldY += speed;
				worldX += speed;
			} else if (keyH.leftPressed) {
				direction = "leftDown";
				worldY += speed;
				worldX -= speed;
			} else {
				direction = "down";
				worldY += speed;
			}
		} else if (keyH.leftPressed == true) {
			direction = "left";
			worldX -= speed;
		} else if (keyH.rightPressed == true) {
			direction = "right";
			worldX += speed;
		}

		if (spriteCounter > 10) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

	public void pickUpObject(int i) {

		if (i != 999) {

			String objectName = gp.obj[i].name;

			switch (objectName) {
			case "Star":
				gp.playSound(0);
				hasStar++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got a star");
				break;
			case "Door":
				if (hasStar > 0) {
					gp.playSound(2);
					gp.obj[i] = null;
					hasStar--;
				}
				break;
				case "Puzzlepiece":
				gp.obj[i]=null;
				gp.ui.showMessage("YOU COMPLETED THE GAME, BUT AT WHAT COST?");
				gp.gameThread = null;
				break;
			case "SDoor":
				if (hasStar > 0) {
					hasStar = 0;
					worldX = 50 * 10;
					worldY = 50 * 10;
					direction = "down";
					switch (gp.obj[i].checker) {
					case 0:
						gp.map = "map5";
						soundOn(3);
						break;
					case 1:
						gp.map = "map6";
						break;
					}
					gp.obj[i] = null;
					gp.mapChange();

				}
			}
		}
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "rightUp":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "leftUp":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "rightDown":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "leftDown":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		}

		g2.drawImage(image, screenX, screenY, 50, 50, null);

	}

	public void soundOn(int i) {
		gp.volumes(-16f);
		gp.playSound(i);
		soundOn = true;
	}

}