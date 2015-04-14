package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class StatusPanel extends JPanel {
	
	private BufferedImage bi;
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public StatusPanel() {
		bi = new BufferedImage(300, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.YELLOW);
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 300, 600);
		
		big.setColor(Color.BLACK);		
		big.drawString(String.format("%08d", reporter.getScore()), 300, 20);
		big.drawString(String.format("%2d", reporter.invulnerable_time()), 100, 20);
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
