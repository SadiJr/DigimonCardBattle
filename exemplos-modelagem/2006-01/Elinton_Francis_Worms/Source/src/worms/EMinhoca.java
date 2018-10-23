package worms;

import javax.microedition.lcdui.Image;

public class EMinhoca {
 
	private String nome = "Jogador 1";	
	private final int STEEP = 1;
	private final int JUMP = 15;                                                                                                                                                                                                                                                                                                                      
	private Image image_right = null;	
	private Image image_left = null;	
	private int posicao_x;	
	private int posicao_y;
	private int Life = 100;
	private EArma arma = null;
	
	public EMinhoca(String nome){
		this.nome = nome;
	}
	
	public void setImages(Image right, Image left){
		image_left =left;
		image_right = right;
	}
	
	public void setPosicaoX(int x){
		posicao_x = x;
	}
	
	public void setPosicaoY(int y){
		posicao_y = y;
	}
	
	public Image getImagem(){
		return image_right;
	}
	
	public Image getImageLeft(){
		return image_left;
	}
	
	public String getNome(){
		return nome;
	}
	public int getSteep(){
		return STEEP;
	}
	
	public int getJump(){
		return JUMP;
	}
	
	public void setDemage(int demage){
		Life-=demage;		
	}
	
	public int getLife(){
		return Life;
	}
	
	public void setArma(EArma arma) {
		this.arma = arma;
	}
	
	public EArma getArma() {
		return arma;
	}
	
	public int getPosicaoX(){
		return posicao_x;
	}
	
	public int getPosicaoY(){
		return posicao_y;
	}
	
}
 
