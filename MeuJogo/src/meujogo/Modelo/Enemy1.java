package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1 { 
	
	private Image imagem;
	private int x,y;
	private int largura, altura;
	private boolean isVisivel;
	
	//private static final int LARGURA = 938; //	Definindo quando o tiro vai sumir
	private static int VELOCIDADE = 10; // Velocidade do tiro
	
	
	
	public  Enemy1(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("src\\res\\Enemy1.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getWidth(null);
	}
	
	public void update() {
		this.x -= VELOCIDADE;
		//if(this.x > LARGURA) { //A largura limite do missel é 938, se ele ultrapassar esse valor ele vai ficar false, quando ele ficar false ele vai sumir
			//isVisivel = false;
		//}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,largura,altura); //Criaremos um retângulo em volta de todos os objetos do jogo para que tenha contato entre eles
		
	}
	
	

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
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
	
	
	
}

//By JeffersonSdev