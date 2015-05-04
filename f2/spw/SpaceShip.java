package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;

public class SpaceShip extends Sprite{
	int hp = 4;
	int step = 8;
	Image img = Toolkit.getDefaultToolkit().getImage("heart.png");

	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		for(int i=0;i<hp-1;i++){
			g.drawImage(img, 475 +i*25, 63, 20, 20, null);
		}
		 
	}

	public void weaken(){
		hp--;
	}

	public void move(int direction){
			x += (step * direction);
			if(x < 0)
				x = 0;
			if(x > 400 - width)
				x = 400 - width;
	}

	public void heal(){
		if(hp<4)
			hp++;
	}

	public void heal_full(){
		hp = 4;
	}

	public void setDefault(){
		super.x = 180;
		super.y = 550;
	}

	public void movey(int ydirection){
			y += (step * ydirection);
			if(y < 0)
				y = 0;
			if(y > 600 - height)
				y = 600 - height;
	}

}
