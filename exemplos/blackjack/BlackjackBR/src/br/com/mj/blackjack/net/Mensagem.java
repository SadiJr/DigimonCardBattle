package br.com.mj.blackjack.net;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Mensagem implements Jogada {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 809501290481359031L;
	
	private String mensagem;

	public Mensagem(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	
}
