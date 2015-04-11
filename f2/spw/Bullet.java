package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Sprite{
	public static final int Y_TO_FADE = 100;
	public static final int Y_TO_DIE = 0;
	
	private int step = 30;
	private boolean alive = true;
	private int direction;
	
	public Bullet(int x, int y,int direction) {
		super(x, y, 10, 10);
		this.direction = direction;
	}

	@Override
	public void draw(Graphics2D g) {
		/*if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}*/
		g.setColor(Color.GREEN);
		g.fillOval(x, y, width, height);
		
	}

	public void proceed(){
		x += direction; 
		y -= step;
		if(y < Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}