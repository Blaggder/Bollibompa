package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Puzzlepiece extends SuperObject{

    public OBJ_Puzzlepiece() {
		
		name = "Puzzlepiece";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/frame.gif"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
