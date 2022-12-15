package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Superdoor extends SuperObject{
	
public OBJ_Superdoor(int i) {
		
		name = "SDoor";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/frames (2).gif"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
		checker = i;
	}

}
