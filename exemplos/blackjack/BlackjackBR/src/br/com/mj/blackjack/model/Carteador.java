package br.com.mj.blackjack.model;

import java.io.Serializable;
/**
 * Classe que representa um carteador
 * @author Jhonatan da Rosa
 */
public class Carteador extends Jogador implements Serializable {

   /**The serial version UID*/
	private static final long serialVersionUID = 2267119606056290623L;
	
	/**
	 * Construtor padrão
	 * @param mesa a mesa do carteador
	 */
	public Carteador(Mesa mesa) {
		super("Carteador", 0, mesa);
	}
	
	/**
	 * A lógica de jogo do carteador.
	 */
	public void jogar(){
		while(true){
			if(this.getValorDaMao() < 17){
				System.out.println(this.getCartaDoBaralho());
			}else{
				System.out.println("Carteador finalizou sua jogada!");
				break;
			}
		}
	}
	
}
