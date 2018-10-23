package worms;

import javax.microedition.lcdui.Image;

public abstract class EBomba{
 
	protected Image imagem;
	 
	protected int raioDaExplosao;
	
	protected int posicaoX;
	
	protected int posicaoY;
	 
	public EBomba(Image imagem, int raioDaExplosao) {
		this.imagem = imagem;
	}
	
	public void setPosicao(int _x, int _y){
		posicaoX = _x;
		posicaoY = _y;
	}
	
	public Image getImagem() {
		return imagem;
	}
	 
	public int getRaioDaExplosao() {
		return raioDaExplosao;
	}
	
	public int[] getPosicao(){
		int posicao[]={posicaoX,posicaoY};
		return posicao;
	}
	
	abstract public int getContagemRegressiva();
	
	abstract public int getAceleracao();
}
 
