package br.com.mj.blackjack.net;

import br.com.mj.blackjack.model.Mesa;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Estado implements Jogada {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7530794325314807653L;
	
	private Mesa mesa;
	
	public Estado(Mesa mesa){
		this.mesa = mesa;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	
}
