package worms;

import javax.microedition.lcdui.Image;

public class EGranada extends EBomba {
 
	private int contagemRegressiva;
	 
	public EGranada(Image imagem, int raioExplosao, int tempo) {
		super(imagem,raioExplosao);
		contagemRegressiva = tempo;
	}
	 
	public int getContagemRegressiva() {
		return contagemRegressiva;
	}
	/**
	 * só implementa pq é obrigado, pois granada nao tem aceleracao
	 * por isso retorna 0
	 */ 
	public int getAceleracao(){
		return 0;
	}
}
 
