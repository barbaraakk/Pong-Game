import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public boolean direita, esquerda;
	
	public int x,y;
	
	public int width, height;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}
	
	public void tick() {
		if(direita) {
			x++;
		}else if(esquerda) {
			x--;
		}
		
		//criar colisão nas paredes
		if(x+width > Game.WIDTH) {
			x = Game.WIDTH - width;
		}else if(x < 0) {
			x = 0;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(177, 179, 181));
		g.fillRect(x, y, width, height);
	}
	
}
