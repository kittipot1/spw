package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*import java.awt.geom.Rectangle2D;
//import java.awt.geom.Rectangle2D.Double;
import java.awt.geom.Ellipse2D;
//import java.awt.geom.Ellipse2D.Double;*/
//import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();	
	private ArrayList<Item> items = new ArrayList<Item>();

	private SpaceShip v;	
	
	private Timer timer;
	private int invulnerable_time=0;
	private int shooting_delay=0;
	private long score = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}

	private void generateBullet(){
		if(shooting_delay==0){
			for(int i=0;i<7;i++){
				Bullet b = new Bullet(v.x, v.y, i*5-15); //(i*5)-10
				gp.sprites.add(b);
				bullets.add(b);
			}
			shooting_delay = 50;
		}
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}

	private void generateItem(){
		Item t = new Item((int)(Math.random()*390), 30);
		gp.sprites.add(t);
		items.add(t);
	}
	
	private void process(){
		if(invulnerable_time>0)
			invulnerable_time--;
		if(shooting_delay>0)
			shooting_delay--;

		if(Math.random() < difficulty){
			generateEnemy();
		}

		if(Math.random() < 0.05){
			generateItem();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 100;
			}
		}

		Iterator<Bullet> b_iter = bullets.iterator();
		while(b_iter.hasNext()){
			Bullet b = b_iter.next();
			b.proceed();
			
			if(!b.isAlive()){
				b_iter.remove();
				gp.sprites.remove(b);
			}
		}

		Iterator<Item> t_iter = items.iterator();
		while(t_iter.hasNext()){
			Item t = t_iter.next();
			t.proceed();
			
			if(!t.isAlive()){
				t_iter.remove();
				gp.sprites.remove(t);
			}
		}
		
		gp.updateGameUI(this);
		
		Ellipse2D.Double vr = v.getCircle();
		Ellipse2D.Double er;
		Ellipse2D.Double br;
		Ellipse2D.Double tr;
		for(Enemy e : enemies){
			er = e.getCircle();
			if(er.intersects(vr.getX(), vr.getY(), vr.getWidth(), vr.getHeight())){
				if(invulnerable_time==0){
					v.weaken();
					invulnerable_time=50;
					if(v.hp==0)
						die();
						return;	
				}
			}
			for(Bullet b : bullets){
				br = b.getCircle();
				if(br.intersects(er.getX(), er.getY(), er.getWidth(), er.getHeight()))
					e.die();
			}
		}
		for(Item t : items){
				tr = t.getCircle();
				if(tr.intersects(vr.getX(), vr.getY(), vr.getWidth(), vr.getHeight()))
					t.die();
					v.heal();
			}
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		case KeyEvent.VK_UP:
			v.movey(-1);
			break;
		case KeyEvent.VK_DOWN:
			v.movey(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_SPACE:
			generateBullet();
			break;
		}
	}

	public long getScore(){
		return score;
	}

	public int invulnerable_time(){
		return invulnerable_time;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
