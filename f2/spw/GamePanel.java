package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.Image;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	//Image img = Toolkit.getDefaultToolkit().getImage("heart.png");

	public GamePanel() {
		bi = new BufferedImage(700, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.WHITE);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 700, 600);
		

		big.setColor(Color.BLACK);	
		big.fillRect ( 400, 0, 300, 600 );
		
		big.setColor(Color.WHITE);		
		big.drawString("HP :", 450, 80);
		big.drawString("SCORE :", 450, 100);
		big.drawString(String.format("%08d", reporter.getScore()), 510, 100);
		big.drawString("Invulnerable :", 450, 120);
		big.drawString(String.format("%2d", reporter.invulnerable_time()), 530, 120);
		for(Sprite s : sprites){
			s.draw(big);
		}
		

		//big.drawImage(img, 450, 50, 20, 20, null); 

		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
