import java.awt.Color;
import java.awt.Graphics;


public class Inimigo {
	
	public double x,y;
	public int width, height;
	
	public Inimigo(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}
	
	public void tick() {
		x += (Game.bola.x - x - 6) * 0.07;
		
		if(x+width > Game.WIDTH) {
			x = Game.WIDTH - width;
		}else if(x < 0) {
			x = 0;
		}
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(114,114,114));
		g.fillRect((int)x, (int)y, width, height);
	}
}
