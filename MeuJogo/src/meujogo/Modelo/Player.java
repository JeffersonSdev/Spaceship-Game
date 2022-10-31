package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener {

	private int x, y; // Todos esses x e y são usados para mover
	private int dx, dy;
	private Image imagem;
	private int altura, largura; // Usado para colisão
	private List<Tiro> tiros;
	private boolean isVisivel, isTurbo;
	
	private Timer timer;

	public Player() { // A coordenada na tela em que o player vai spawnar
		this.x = 100;
		this.y = 100;
		isVisivel = true;
		isTurbo = false;

		tiros = new ArrayList<Tiro>();

		timer = new Timer(5000, this); //5000 que é equivalente a 5 seg
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isTurbo == true) {	
		turbo();
		isTurbo = false;
		
		}
		if(isTurbo == false) {
			load();
		}
		
	}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void load() { // Esse método vai ser usado para definir a imagem do nosso player
		ImageIcon referencia = new ImageIcon("src\\res\\spaceship.png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null); // Definindo a altura do play
		largura = imagem.getWidth(null); // Definindo a largura do play
	}

	public void update() { // Metodo para atualizar a tela
		x += dx; // Vai fazer com que a imagem da nave se mova no eixo x ou no eixo y
		y += dy;

	}

	public void tiroSimples() {
		this.tiros.add(new Tiro(x - (largura + 540), y + (altura - 482))); // Para o tiro aparecer exatamente no meio da
																			// nave (posição de onde vai sair o tiro)
	}

	public void turbo() {
		isTurbo = true;
		ImageIcon referencia = new ImageIcon("src\\res\\spaceship_turbo.png");
		imagem = referencia.getImage();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura); // Criaremos um retângulo em volta de todos os objetos do jogo para
														// que tenha contato entre eles

	}

	public void keyPressed(KeyEvent tecla) { // Quando for pressionado uma tecla do teclado ele vai fazer alguma ação
		int codigo = tecla.getKeyCode();

		//Ação para soltar o turbo
		if (codigo == KeyEvent.VK_SPACE) { //Utilizando a tecla espaço o turbo será acionado
			turbo();
		}
		// Ação para disparar os tiros
		if (codigo == KeyEvent.VK_F) { // Utilizando a tecla F o tiro será disparado
			tiroSimples();
		}
		// Ação para movimentação da nave

		if (codigo == KeyEvent.VK_W) { // Para cima
			dy = -15;
		}

		if (codigo == KeyEvent.VK_S) { // Para baixo
			dy = 15;
		}

		if (codigo == KeyEvent.VK_A) { // Para esquerda
			dx = -15;
		}

		if (codigo == KeyEvent.VK_D) { // Para direita
			dx = 15;
		}

	}

	public void keyRelease(KeyEvent tecla) { // Quando eu parar de pressionar a tecla o dy e o dx vai virar zero de
												// novo, assim a nave vai parar
		int codigo = tecla.getKeyCode();

		// Ação para movimentação da nave

		if (codigo == KeyEvent.VK_W) { // Para cima
			dy = 0;
		}

		if (codigo == KeyEvent.VK_S) { // Para baixo
			dy = 0;
		}

		if (codigo == KeyEvent.VK_A) { // Para esquerda
			dx = 0;
		}

		if (codigo == KeyEvent.VK_D) { // Para direita
			dx = 0;
		}

	}

	 
	public boolean isTurbo() {
		return isTurbo;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

}


//By JeffersonSdev