
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Pacman {

	private BufferedImage 	pacman, pacman1, pacman2, pacmanLeft1, pacmanLeft2, pacmanRight1, 
							pacmanRight2, pacmanUp1, pacmanUp2, pacmanDown1, pacmanDown2;
	
	private Frame f = null;
	private int posX, posY;
	private int step = Dados.STEP;
	
	public Pacman (Graphics g, Frame f) {
		try {
		
			pacmanLeft1 = ImageIO.read (getClass().getResource("/pictures/PacmanLeft1.gif"));
			pacmanLeft2 = ImageIO.read (getClass().getResource("/pictures/PacmanLeft2.gif"));
			pacmanRight1= ImageIO.read (getClass().getResource("/pictures/PacmanRight1.gif"));
			pacmanRight2= ImageIO.read (getClass().getResource("/pictures/PacmanRight2.gif"));
			pacmanUp1	= ImageIO.read (getClass().getResource("/pictures/PacmanUp1.gif"));
			pacmanUp2	= ImageIO.read (getClass().getResource("/pictures/PacmanUp2.gif"));
			pacmanDown1	= ImageIO.read (getClass().getResource("/pictures/PacmanDown1.gif"));
			pacmanDown2	= ImageIO.read (getClass().getResource("/pictures/PacmanDown2.gif"));
			
		} catch (IOException e) {
			System.out.println("Erro no carregamento da Imagem!!");
			System.exit(0);			
		}
		
			pacman1 = pacmanLeft1;
			pacman2 = pacmanLeft2;
			this.f = f;
			
			this.setXY(Dados.INI_PACMAN_XY);
	}

	public void desenhaPacman (Graphics bufferG) {
		if(pacman == pacman1){
			pacman = pacman2;
		} else {
			pacman = pacman1;
		}
		bufferG.drawImage(pacman, posX, posY, f);
	}
	
	public void moveLeft (){
		posX-=step;
		pacman1 = pacmanLeft1;
		pacman2 = pacmanLeft2;
	}
	
	public void moveRight(){
		posX+=step;;
		pacman1 = pacmanRight1;
		pacman2 = pacmanRight2;
	}
	
	public void moveUp   (){
		posY-=step;;
		pacman1 = pacmanUp1;
		pacman2 = pacmanUp2;
	}
	
	public void moveDown (){
		posY+=step;;
		pacman1 = pacmanDown1;
		pacman2 = pacmanDown2;
	}
	
	public void setXY (int [] XY){
		this.posX = XY[0];
		this.posY = XY[1];
	}
	
	public int [] getXY (){
		int XY [] = {posX, posY};
		return XY;
	}

}
