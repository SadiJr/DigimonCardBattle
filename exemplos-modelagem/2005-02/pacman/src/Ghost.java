
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Ghost  {

	private BufferedImage 	ghost, ghost1, ghost2, ghostLeft1, ghostLeft2, ghostRight1, 
							ghostRight2, ghostUp1, ghostUp2, ghostDown1, ghostDown2,
							ghostScared1, ghostScared2;
	private Frame f = null;
	private int posX, posY;
	private int step = Dados.BLOCKSIZE/7;
	private byte ghostDir;
	private byte ghostLastDir;
	private ChecaLimite checalimite;
	
	public Ghost (Graphics g, Frame f, ChecaLimite checalimite) {
		try {
			
			// Imagens do Fantasma NÃO assustado:
			ghostLeft1  = ImageIO.read (getClass().getResource("/pictures/GhostLeft1.gif"));
			ghostLeft2  = ImageIO.read (getClass().getResource("/pictures/GhostLeft2.gif"));
			ghostRight1 = ImageIO.read (getClass().getResource("/pictures/GhostRight1.gif"));
			ghostRight2 = ImageIO.read (getClass().getResource("/pictures/GhostRight2.gif"));
			ghostUp1	= ImageIO.read (getClass().getResource("/pictures/GhostUp1.gif"));
			ghostUp2	= ImageIO.read (getClass().getResource("/pictures/GhostUp2.gif"));
			ghostDown1	= ImageIO.read (getClass().getResource("/pictures/GhostDown1.gif"));
			ghostDown2	= ImageIO.read (getClass().getResource("/pictures/GhostDown2.gif"));
			
			// Imagens do Fantasma assustado:
			ghostScared1 = ImageIO.read (getClass().getResource("/pictures/GhostScared1.gif"));
			ghostScared2 = ImageIO.read (getClass().getResource("/pictures/GhostScared2.gif"));
			
		}
		catch(IOException e) {
			System.out.println("Erro no carregamento da Imagem!!");
			System.exit(0);
		}
		
		this.ghost1 = this.ghostLeft1;
		this.ghost2 = this.ghostLeft2;
		this.f = f;
		this.ghostLastDir = Dados.NONE;
		this.checalimite = checalimite;
		
		this.setXY(Dados.INI_GHOST_XY);
	}
	
	// Método que desenha o Fantasma:
	public void desenhaGhost (Graphics bufferG) {
		if(ghost == ghost1){
			ghost = ghost2;
		} else {
			ghost = ghost1;
		}
		bufferG.drawImage(ghost, posX, posY, f);
	}
	
	// GHOST BURRO:
	public byte estrategiaB(int ghostXY[]){
		byte lastDir=Dados.NONE;
		do{
			ghostDir = (byte)(((Math.random()*20)/5)+1);
		} while(checalimite.checaLimite(ghostXY,ghostDir)==true || ghostDir == lastDir);
		lastDir = ghostDir;
		return ghostDir;
	}
	
	public void move (boolean isScared) {
		
		if((posX%Dados.BLOCKSIZE==0&&posY%Dados.BLOCKSIZE==0) && checalimite.checaLimite(this.getXY(),ghostDir)){
			ghostDir = this.estrategiaB(this.getXY());
			ghostLastDir = ghostDir;
		} else {
			ghostDir = ghostLastDir;	
		}
		
		// Caso o Fantasma NÃO esteja assutado:
			switch(ghostDir) {
			
				case Dados.NONE: { break; }
			
			// Fantasma move-se à esquerda:
				case Dados.LEFT:{
						posX -= step;
						this.ghost1 = this.ghostLeft1;
						this.ghost2 = this.ghostLeft2;
					break;
				}
			
			// Fantasma move-se à direita:
				case Dados.RIGHT:{
						posX += step;
						this.ghost1 = this.ghostRight1;
						this.ghost2 = this.ghostRight2;
					break;
				}
				
			// Fantasma move-se para cima:
				case Dados.UP:{
						posY -= step;
						this.ghost1 = this.ghostUp1;
						this.ghost2 = this.ghostUp2;
					break;
				}
			
			// Fantasma move-se para baixo:
				case Dados.DOWN:{
						posY += step;
						this.ghost1 = this.ghostDown1;
						this.ghost2 = this.ghostDown2;						
					break;
				}
			}
			if(isScared){
				this.ghost1 = this.ghostScared1;
				this.ghost2 = this.ghostScared2;
		}	
	}
	
	public void setLastDir (byte lastDir){
		this.ghostLastDir = lastDir;
	}
	
	public byte getLastDir (){
		return this.ghostLastDir;
	}
	
	public void setXY (int [] XY){
		posX = XY[0];
		posY = XY[1];
	}
	
	// Método que retorna a posição XY do Fantasma:
	public int [] getXY (){
		int XY [] = {posX, posY};
		return XY;
	}
	
	
	
}
