package worms;

import javax.microedition.lcdui.Image;

public abstract class EArma {
 
	protected int quantidadeTirosPorLance;
	 
	protected Image imagem;
	
	protected int anguloInclinamento =  10;
	
	protected int velocidadeDoDisparo = 20;
	 
	public EArma(Image imagem, int tirosPorLance) {
		this.imagem = imagem;
		quantidadeTirosPorLance = tirosPorLance;
	}
	
	public void setVelocidadeDisparo(int velocidade){
		this.velocidadeDoDisparo = velocidade;
	}
	
	public void setAnguloDisparo(int angulo) {
		this.anguloInclinamento = angulo;
	}
	
	public int getVelocidadeDisparo(){
		return velocidadeDoDisparo;
	}
	
	public int getAnguloDisparo(){
		return anguloInclinamento;
	}
	
	public Image getImagem(){
		return imagem;
	}
	 
	public int getTirosLance(){
		return quantidadeTirosPorLance;
	}
	
	public abstract EBomba getBomba();
	
	public abstract Image getImagem15graus();
	
	public abstract Image getImagem30graus();
	
	public abstract Image getImagem45graus();
	
	public abstract Image getImagem60graus();
	
	public abstract Image getImagem75graus();
	
	

}
 
