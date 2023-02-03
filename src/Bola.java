
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bola {
	
	public double x,y;
	public int width, height;
	
	public double dx, dy;
	public double velocidade = 1.7;
	
	public Bola(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		
		int angle = new Random().nextInt(120-45)+ 45 + 1;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));

	
	}
	
	public void tick() {
		
		if(x+(dx*velocidade) + width >= Game.WIDTH) {
			dx*=-1;
		}else if(x+(dx*velocidade) < 0) {
			dx*=-1;
		}
		
		if(y >= Game.HEIGHT) {
			System.out.println("Ponto do inimigo!");
			new Game();
			return;
		}else if(y < 0) {
			//ponto jogador
			System.out.println("Fez um ponto!");
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*velocidade)),(int) (y+(dy*velocidade)), width, height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsInimigo = new Rectangle((int)Game.inimigo.x,(int) Game.inimigo.y, Game.inimigo.width, Game.inimigo.height);
		
		if(bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(120-45)+ 45 + 1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy > 0)
				dy*=-1;	
		}else if(bounds.intersects(boundsInimigo)) {
			int angle = new Random().nextInt(120-45)+ 45 + 1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy < 0)
				dy*=-1;	
		}
		
		x+=dx*velocidade;
		y+=dy*velocidade;
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(244, 243, 239));
		g.fillRect((int)x, (int)y, width, height);
	}
}
