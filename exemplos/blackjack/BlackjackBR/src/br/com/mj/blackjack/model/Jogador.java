package br.com.mj.blackjack.model;

import java.io.Serializable;
import java.util.List;

/**
 * A classe que representa um Jogador do jogo blackjack
 * 
 * @author Jhonatan da Rosa
 * @version v1.0 26/04/2009
 */
public class Jogador implements Serializable {
   /**The serial version UID*/
	private static final long serialVersionUID = 3801655024443236005L;
	/** O nome do jogador */
	private String nome;
	/** O número do jogador */
	private int numero;
	/** A mão do jogador */
	private Mao mao;
	/** A mesa que este jogador pertence */
	private Mesa mesa;
	/** Indica se o jogador está fora do jgo */
	private boolean fora = false;


	/**
	 * O construtor
	 * 
	 * @param nome
	 * @param numero
	 * @param mesa
	 */
	public Jogador(String nome, int numero, Mesa mesa) {
		this.nome = nome;
		this.numero = numero;
		this.mesa = mesa;
		this.mao = new Mao();
	}

	/**
	 * Pega uma carta do topo do baralho e adiciona a mão do jogador
	 * 
	 * @return Uma string que informa que carta o jogador pegou
	 */
	public String getCartaDoBaralho() {
		Carta carta = this.mesa.getBaralho().getCartaTopo();
		this.mao.adicionaCarta(carta);
		return String.format("%s pega a carta %s!", this.nome, carta);
	}

	/**
	 * Retorna uma lista das cartas na mão do jogador
	 * 
	 * @return a lista
	 */
	public List<Carta> getCartasDaMao() {
		return this.mao.getCartas();
	}

	/**
	 * Soma o valor das cartas na mão do jogador
	 * 
	 * @return o valor
	 */
	public int getValorDaMao() {
		int i = 0;
		int indiceAs = -1;
		for (Carta carta : this.mao.getCartas()) {
			if (carta.getNumero().equals(NumeroCarta.AS) && indiceAs == -1) {
				indiceAs = this.mao.getCartas().indexOf(carta);
			} else {
				i += carta.getValor();
			}
		}
		if (indiceAs != -1) {
			Carta as = this.mao.getCartas().get(indiceAs);
			if (i < 11) {
				i += as.getSegundoValor();
			} else {
				i += as.getValor();
			}
		}
		return i;
	}

	/**
	 * Limpa a mão do jogador
	 */
	public void limparMao() {
		this.mao.limpar();
	}

	/**
	 * O jogador representado em um objeto String
	 * 
	 * @return "Jogador %s" - onde %s é o nome do jogador
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("Jogador %s", this.nome);
	}

	/**
	 * Seta se o jogador está fora
	 * @param fora
	 */
	public void setFora(boolean fora) {
		this.fora = fora;
	}

	/**
	 * Se o jogador está fora
	 */
	public boolean isFora() {
		return this.fora;
	}
	/**
	 * Se ele possui um blackjack
	 */
	public boolean isBlackjack() {
		return this.mao.isBlackjack();
	}
	/**
	 * Seta se o jogador possui um blackjack
	 * @param blackjack
	 */
	public void setBlackjack(boolean blackjack) {
		this.mao.setBlackjack(blackjack);
	}
	/**
	 * Se ele possui cinco cartas charlie
	 */
	public boolean isCincoCartasCharlie() {
		return this.mao.isCincoCartasCharlie();
	}
	/**
	 * Seta se o jogador possui cinco cartas charlie
	 * @param cincoCartasCharlie
	 */
	public void setCincoCartasCharlie(boolean cincoCartasCharlie) {
		this.mao.setCincoCartasCharlie(cincoCartasCharlie);
	}
	/**
	 * Retorna o nome do jogador
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Retorna o número do jogador
	 */
	public int getNumero() {
		return numero;
	}

}// fim da classe Jogador
