package br.ufsc.inf.model;

import java.io.Serializable;

public class Carta implements Serializable {

	private static final long serialVersionUID = 1L;

	protected int numero;
	protected String naipe;
	protected boolean revelada;

	public Carta(int numero, String naipe) {
		this.numero = numero;
		this.naipe = naipe;
		this.revelada = false;
	}

	public String getNome() {
		return this.numero + this.naipe;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setRevelada(boolean revelada) {
		this.revelada = revelada;
	}

	public boolean isRevelada() {
		return this.revelada;
	}

}