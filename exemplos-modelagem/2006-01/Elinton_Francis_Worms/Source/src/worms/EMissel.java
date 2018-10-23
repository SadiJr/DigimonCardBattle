package worms;

import javax.microedition.lcdui.Image;

public class EMissel extends EBomba {
 
	protected int aceleracao = 0;
	 
	public EMissel(Image imagem, int raioExplosao, int aceleracao) {
		super(imagem,raioExplosao);
		this.aceleracao = aceleracao;
	}
	 
	public int getAceleracao() {
		return aceleracao;
	}
	
	public int getContagemRegressiva() {
		return 0;
	}
	 
}
 
