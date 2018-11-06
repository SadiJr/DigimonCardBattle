package br.com.mj.blackjack.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * A classe que representa a mão do jogador
 * Contém uma lista de cartas
 * @author Jhonatan da Rosa
 * @version v1.0, 25/04/2009
 */
public class Mao implements Serializable {
   /**The serial version UID*/
	private static final long serialVersionUID = -5597016716737477665L;
	/**As cartas na mão do jogador*/
	private List<Carta> cartas;
	/**Se é blackjack*/
	private boolean blackjack = false;
	/**Se é Cinco Cartas Charlie*/
	private boolean cincoCartasCharlie = false;
	/**
	 * O construtor padrão
	 */
	public Mao(){
		this.cartas = new ArrayList<Carta>();
	}
	/**
	 * Adiciona uma carta a mão
	 * @param carta
	 */
	public void adicionaCarta(Carta carta){
		this.cartas.add(carta);
	}
	/**
	 * Remove uma carta da mão
	 * @param carta a carta a ser removida
	 * @return a carta removida.
	 */
	public Carta removeCarta(Carta carta){
		return this.cartas.remove(this.cartas.indexOf(carta));
	}
	/**
	 * Retorna uma lista com as cartas na mão
	 * @return as cartas
	 */
	public List<Carta> getCartas(){
		return this.cartas;
	}
	/**
	 * Limpa a lista de cartas da mão
	 */
	public void limpar() {
	   this.cartas.clear();
	}
	
	/**
	 * Se é blackjack
	 */
	public boolean isBlackjack() {
		return blackjack;
	}
	/**
	 * Seta se é blackjack
	 * @param blackjack
	 */
	public void setBlackjack(boolean blackjack) {
		this.blackjack = blackjack;
	}
	/**
	 * Se é Cinco Cartas Charlie
	 */
	public boolean isCincoCartasCharlie() {
		return cincoCartasCharlie;
	}
	/**
	 * Seta se é Cinco Cartas Charlie
	 * @param cincoCartasCharlie
	 */
	public void setCincoCartasCharlie(boolean cincoCartasCharlie) {
		this.cincoCartasCharlie = cincoCartasCharlie;
	}
	
	
}//fim da classe Mao