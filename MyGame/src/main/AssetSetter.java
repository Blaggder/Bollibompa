package main;

import object.OBJ_Door;
import object.OBJ_Star;
import object.OBJ_Superdoor;
import object.OBJ_Tree;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

public void OBJ(String s,int i,int x, int y,int c) {
	switch(s) {
	case"star":
		gp.obj[i] = new OBJ_Star();
		gp.obj[i].worldX = x * 50;
		gp.obj[i].worldY = y * 50;
	
break;
	case "door":
		gp.obj[i] = new OBJ_Door();
		gp.obj[i].worldX = x * 50;
		gp.obj[i].worldY = y * 50;
break;
	case "superdoor":
		gp.obj[i] = new OBJ_Superdoor(c);
		gp.obj[i].worldX = x * 50;
		gp.obj[i].worldY = y * 50;
break;
	case "tree":
		gp.obj[i] = new OBJ_Tree();
		gp.obj[i].worldX = x * 50;
		gp.obj[i].worldY = y * 25;
	break;
	}
}

	public void setObject() {
		switch (gp.map) {
		case "map4":
			OBJ("star",0, 2, 2,0);
			OBJ("star",6, 5, 40,0);
			OBJ("door",1, 25, 5,0);
			OBJ("superdoor",7, 48, 39, 0);
			OBJ("tree",2, 2, 10,0);
			OBJ("tree",3, 2, 20,0);
			OBJ("tree",4, 3, 10,0);
			OBJ("tree",5, 3, 20,0);
			break;
		case "map5":
			OBJ("star",8, 30, 35,0);
			OBJ("star",9, 7, 45,0);
			OBJ("door",11, 25, 20,0);
			OBJ("superdoor",10, 47, 45, 1);
			break;
		}
	}
}
