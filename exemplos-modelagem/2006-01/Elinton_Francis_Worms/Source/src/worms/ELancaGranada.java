package worms;

import javax.microedition.lcdui.Image;

public class ELancaGranada extends EArma {
 
	protected EGranada granada;
	 
	public ELancaGranada(Image imagem, int tirosPorLance, EGranada granada) {
		super(imagem,tirosPorLance);
		this.granada = granada;		
	}
	 
	public EBomba getBomba() {
		return granada;
	}
	
	public Image getImagem15graus(){
		return super.getImagem();
	}
	
	public Image getImagem30graus(){
		return super.getImagem();
	}
	
	public Image getImagem45graus(){
		return super.getImagem();
	}
	public Image getImagem60graus(){
		return super.getImagem();
	}
	
	public Image getImagem75graus(){
		return super.getImagem();
	}
	 
}
 
