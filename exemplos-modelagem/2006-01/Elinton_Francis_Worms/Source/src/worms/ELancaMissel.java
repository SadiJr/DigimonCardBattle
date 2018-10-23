package worms;

import javax.microedition.lcdui.Image;

public class ELancaMissel extends EArma {
 
	protected EMissel missel;
	
	protected Image imagem15graus;
	
	protected Image imagem30graus;
	
	protected Image imagem45graus;

	protected Image imagem60graus;

	protected Image imagem75graus;
	
	public ELancaMissel(Image img, int tirosPorLance, EMissel missel, Image img15,
						Image img30, Image img45, Image img60, Image img75) {
		super(img,tirosPorLance);
		this.missel = missel;
		this.imagem15graus = img15;
		this.imagem30graus = img30;
		this.imagem45graus = img45;
		this.imagem60graus = img60;
		this.imagem75graus = img75;
	}
	 
	public EBomba getBomba() {
		return missel;
	}
	
	/**
	 * Override implementação da classe Pai
	 * @return int velocidade inicial do disparo
	 */
	public int getVelocidadeDisparo(){
		return super.getVelocidadeDisparo();
	}
	
	public Image getImagem15graus(){
		return imagem15graus;
	}
	
	public Image getImagem30graus(){
		return imagem30graus;
	}
	
	public Image getImagem45graus(){
		return imagem45graus;
	}
	public Image getImagem60graus(){
		return imagem60graus;
	}
	
	public Image getImagem75graus(){
		return imagem75graus;
	}
	 
}
 
