package view;

import javax.swing.JLabel;

public class LabelImg extends JLabel {

	protected int posicao;
	protected String imagem;
	protected String imagem2;

	public LabelImg(int posicao) {
		this.posicao = posicao;
	}

	public String retornaImagem() {
		if (this.imagem2 == null) {
			return imagem;
		} else {
			return imagem2;
		}
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getImagem2() {
		return imagem2;
	}

	public void setImagem2(String imagem2) {
		this.imagem2 = imagem2;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

}