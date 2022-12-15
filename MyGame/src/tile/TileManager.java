package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	//int mapTileNum2[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[50][50];
		//mapTileNum2 = new int[20][10];
		
		getTileImage();
		
		loadMap(gp.map);
		//loadMap2();
	}
	
	public void getImage(int x,String s,boolean b) {
		try {
			tile[x] = new Tile();
			tile[x].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+s+".png"));
			tile[x].collision = b;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void getTileImage() {
		
		getImage(0,"grass2",false);
		getImage(2,"water",true);
		getImage(3,"lava",true);
		getImage(4,"stone",false);
		getImage(1,"sand",false);
		getImage(5,"tree2",false);	
		getImage(6,"blank",false);
		getImage(7,"tree",false);
		getImage(8,"grass",false);
		getImage(9,"stone-1",true);
		
	}
	public void loadMap(String s) {
		
		try {
			InputStream is = getClass().getResourceAsStream("/maps/"+s+".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < 20 && row < 50) {
				
				String line = br.readLine();
				
				while(col< 50) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col==50) {
					col= 0;
					row++; 
				}
			}
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
/*public void loadMap2() {
		
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map3.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < 20 && row < 10) {
				
				String line = br.readLine();
				
				while(col< 20) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum2[col][row] = num;
					col++;
				}
				if(col==20) {
					col= 0;
					row++; 
				}
			}
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	public void draw(Graphics2D g2) {	
		
		int worldCol = 0;
		int worldRow = 0;
		
		
		while(worldCol < 50 && worldRow < 50) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * 50;
			int worldY = worldRow * 50;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(		worldX + 50 > gp.player.worldX - gp.player.screenX && 
					worldX - 50 < gp.player.worldX + gp.player.screenX &&
					worldY + 50 > gp.player.worldY - gp.player.screenY && 
					worldY - 50 < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX,screenY,50,50,null); 
			}
			
			worldCol++;
			
			
			if(worldCol== 50) {
				worldCol=0;
				worldRow++;
				
			}
		}
		
	}
		
	
		/*public void draw2(Graphics2D g2) {	
			
			int col = 0;
			int row = 0;
			int x = 0;
			int y = 0;
			
			while(col < 20 && row < 10) {
				
				int tileNum = mapTileNum2[col][row];
				
				g2.drawImage(tile[tileNum].image, x,y,50,100,null); 
				col++;
				x += 50;
				
				if(col== 20) {
					col=0;
					x=0;
					row++;
					y+=50;
				}
			}

		
	
		
		
		
	}*/
}
