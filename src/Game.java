import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	
	private static final long serialVersionUID = 1L;
	private boolean isRunning = true;
	//tamanho da janela
	public static int WIDTH = 160;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	//instancia as classes
	public static Player player;
	public static Inimigo inimigo;
	public static Bola bola;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);
		player = new Player(100,HEIGHT-5);
		inimigo = new Inimigo(100, 0);
		bola = new Bola(100, HEIGHT/2 - 1);
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		//para não poder redimensionar a janela
		frame.setResizable(false);
		//fechar a janela e não deixar ela em segundo plano quando o jogo fechar
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		//deixa a janela sempre no centro da tela
		frame.setLocationRelativeTo(null);
		//deixar visivel por que por padrão ele não aparece
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	
	public void tick() {
		player.tick();
		inimigo.tick();
		bola.tick();
	}
	
	
	public void render() {
		//buffer strategy é aonde renderiza tudo no jogo
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		 
		Graphics g = layer.getGraphics();
		g.setColor(new Color(19, 19, 19));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		player.render(g);
		inimigo.render(g);
		bola.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		//mostra na tela
		bs.show();
	}
	
	
	@SuppressWarnings("unused")
	@Override
	public void run() {
		//pega o tempo atual do pc em nano segundo precisamente
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		//dividindo 1 segundo em formato de nano pelo amount of ticks
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		//ver se ta mesmo funcionando  a 60fps
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now =  System.nanoTime();
			//diminui o tempo atual - o da ultima vez
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				//sempre atualiza o jogo (tick) antes de renderizar (render)
				tick();
				render();
				//ver se o fps ta funcionando
				//frames++;
				//pra dizer que chegou no 1 segundo ou maior
				delta--;
			}
					
			//fps - se o currenttimemillis - timer for >= a 1000 quer dizer que passou 1 segundo apos a ultima vez que mostro a msg
			if(System.currentTimeMillis() - timer >= 1000) {
				//concatena pra mostrar o fps
				//System.out.println("FPS: "+ frames);
				frames = 0;
				//pra fazer que mostre a cada segundo o fps:
				//timer = System.currentTimeMillis();
				//ou (assim fica mais leve)
				timer += 1000;
			}
		}
	
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//definir qual tecla vai ser usada para o jogo
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.direita = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.esquerda = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.direita = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.esquerda = false;
		}
		
	}

	
}
