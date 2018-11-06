package br.com.mj.blackjack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * A classe que representa a m�o do jogador
 * Cont�m uma lista de cartas
 * @author Jhonatan da Rosa
 * @version v1.0, 25/04/2009
 */
public class Mao implements Serializable {
   /**The serial version UID*/
	private static final long serialVersionUID = -5597016716737477665L;
	/**As cartas na m�o do jogador*/
	private List<Carta> cartas;
	/**Se � blackjack*/
	private boolean blackjack = false;
	/**Se � Cinco Cartas Charlie*/
	private boolean cincoCartasCharlie = false;
	/**
	 * O construtor padr�o
	 */
	public Mao(){
		this.cartas = new ArrayList<Carta>();
	}
	/**
	 * Adiciona uma carta a m�o
	 * @param carta
	 */
	public void adicionaCarta(Carta carta){
		this.cartas.add(carta);
	}
	/**
	 * Remove uma carta da m�o
	 * @param carta a carta a ser removida
	 * @return a carta removida.
	 */
	public Carta removeCarta(Carta carta){
		return this.cartas.remove(this.cartas.indexOf(carta));
	}
	/**
	 * Retorna uma lista com as cartas na m�o
	 * @return as cartas
	 */
	public List<Carta> getCartas(){
		return this.cartas;
	}
	/**
	 * Limpa a lista de cartas da m�o
	 */
	public void limpar() {
	   this.cartas.clear();
	}
	
	/**
	 * Se � blackjack
	 */
	public boolean isBlackjack() {
		return blackjack;
	}
	/**
	 * Seta se � blackjack
	 * @param blackjack
	 */
	public void setBlackjack(boolean blackjack) {
		this.blackjack = blackjack;
	}
	/**
	 * Se � Cinco Cartas Charlie
	 */
	public boolean isCincoCartasCharlie() {
		return cincoCartasCharlie;
	}
	/**
	 * Seta se � Cinco Cartas Charlie
	 * @param cincoCartasCharlie
	 */
	public void setCincoCartasCharlie(boolean cincoCartasCharlie) {
		this.cincoCartasCharlie = cincoCartasCharlie;
	}
	
	
}//fim da classe Mao