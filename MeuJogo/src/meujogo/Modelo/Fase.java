package meujogo.Modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<Stars> stars;
	private boolean emJogo;
	
	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("src\\res\\Blackground.jpg");
		fundo = referencia.getImage();

		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this);
		timer.start();

		spawInimigos();
		spawStars();
		emJogo = true;
	}

	public void spawInimigos() {
		int coordenadas[] = new int[40];
		enemy1 = new ArrayList<Enemy1>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 8000 + 1050); // Serve para aparecer randomicamente dentro do parâmetro da
															// tela
			int y = (int) (Math.random() * 1050 + 20);
			enemy1.add(new Enemy1(x, y));
		}
	}

	public void spawStars() {
		int coordenadas[] = new int[300];
		stars = new ArrayList<Stars>();
		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 1400 + 0); // Serve para aparecer randomicamente dentro do parâmetro da
														// tela
			int y = (int) (Math.random() * 1050 + 0);
			stars.add(new Stars(x, y));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;

		if (emJogo == true) {

			graficos.drawImage(fundo, 0, 0, null);
			for (int p = 0; p < stars.size(); p++) {
				Stars q = stars.get(p);
				q.load();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			}

			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
			List<Tiro> tiros = player.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int e1 = 0; e1 < enemy1.size(); e1++) {
				Enemy1 in = enemy1.get(e1);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

			}
		} else {
			ImageIcon fimJogo = new ImageIcon("src\\res\\GameOver.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		
		if (player.isTurbo()) {
			timer.setDelay(1);
		}
		
		if (player.isTurbo() == false) {
			timer.setDelay(5);
		}

		for (int p = 0; p < stars.size(); p++) {
			Stars on = stars.get(p);
			if (on.isVisivel()) {
				on.update();
			} else {
				stars.remove(p);
			}
		}

		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}

		for (int e1 = 0; e1 < enemy1.size(); e1++) {
			Enemy1 in = enemy1.get(e1);
			if (in.isVisivel()) {
				in.update();
			} else {
				enemy1.remove(e1);
			}
		}
		checarColisoes();
		repaint();

	}

	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaEmemy1;
		Rectangle formaTiro;

		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEmemy1 = tempEnemy1.getBounds();

			if (formaNave.intersects(formaEmemy1)) {
				player.setVisivel(false);
				tempEnemy1.setVisivel(false);
				emJogo = false;
			}
		}

		List<Tiro> tiros = player.getTiros();
		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBounds();
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 tempEnemy1 = enemy1.get(o);
				formaEmemy1 = tempEnemy1.getBounds();
				if (formaTiro.intersects(formaEmemy1)) {
					tempEnemy1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}

	}

	private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);

		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}

	}
}

//By JeffersonSdev