package model;

import br.ufsc.inf.leobr.cliente.Jogada;

import javax.swing.ImageIcon;

import model.TipoCarta.Naipe;
import model.TipoCarta.Cor;

public class Carta implements Jogada {

	protected int numero;
	protected Naipe naipe;
	protected Cor cor;
	protected ImageIcon imagem;

	public Carta(int numero, Naipe naipe, Cor cor, ImageIcon imagem) {
		super();
		this.numero = numero;
		this.naipe = naipe;
		this.cor = cor;
		this.imagem = imagem;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Naipe getNaipe() {
		return naipe;
	}

	public void setNaipe(Naipe naipe) {
		this.naipe = naipe;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}
	
	public ImageIcon getImage() {
		return imagem;
	}
	
	public void setImage(ImageIcon imagem) {
		this.imagem = imagem;
	}
}
