package f2.spw;

import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Space War");
		JPanel panel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 650);
		frame.getContentPane().setLayout(new BorderLayout());
		
		SpaceShip v = new SpaceShip(180, 550, 20, 20);
		GamePanel gp = new GamePanel();
		StatusPanel ab = new StatusPanel();
		GameEngine engine = new GameEngine(gp, v);
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.WEST);
		panel.setPreferredSize(new Dimension(300, 600));
		panel.setBackground(Color.GREEN);
		frame.getContentPane().add(panel, BorderLayout.EAST);
		frame.setVisible(true);
		
		engine.start();
	}
}
