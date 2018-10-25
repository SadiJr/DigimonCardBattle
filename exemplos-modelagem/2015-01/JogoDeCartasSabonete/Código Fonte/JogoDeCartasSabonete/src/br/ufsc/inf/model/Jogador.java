package br.ufsc.inf.model;

public class Jogador {

	protected String nome;
	protected Carta cartaMao;
	protected boolean daVez;

	public Jogador(String nome) {
		this.nome = nome;
		this.daVez = false;
	}

	public Carta getCartaMao() {
		return this.cartaMao;
	}

	public void setCartaMao(Carta carta) {
		this.cartaMao = carta;
	}

	public boolean isDaVez() {
		return this.daVez;
	}

	public void setDaVez(boolean daVez) {
		this.daVez = daVez;
	}

}