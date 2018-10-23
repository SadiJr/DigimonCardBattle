/**
 * 
 */
package br.com.mj.blackjack.model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


/**
 * A classe que representa um baralho
 * 
 * @author Jhonatan da Rosa
 * @version v1.0, 25/04/2009
 */
public class Baralho implements Embaralhavel,Serializable{
   /**The serial version UID*/
	private static final long serialVersionUID = 5571720429162087399L;
	/**As cartas*/
	private List<Carta> cartas;
	/**
	 * O construtor padrão
	 * Inicializa as 52 cartas.
	 */
	public Baralho(){
		//Cria a lista de cartas
		this.cartas = new ArrayList<Carta>();
		//Cria as cartas
		for(Naipe naipe : Naipe.values()){
			for(NumeroCarta number : NumeroCarta.values()){
				this.cartas.add(new Carta(naipe,number));
			}
		}
	}//fim do construtor
	/**
	 * Pega uma carta do baralho
	 * @param indice indice da carta
	 * @return a carta neste indice
	 */
	public Carta getCarta(int indice){
		return this.cartas.get(indice);
	}
	/**
	 * Seta uma carta no baralho
	 * Troca a carta no indice pela nova carta. 
	 * @param indice indice da carta
	 * @param carta a carta
	 */
	public void setCarta(int indice,Carta carta){
		this.cartas.set(indice, carta);
	}
	/**
	 * Remove a carta do baralho
	 * @param indice o indice da carta
	 * @return a carta neste indice
	 */
	public Carta removerCarta(int indice){
		return this.cartas.remove(indice);
	}
	/**
	 * Retorna a carta do topo do baralho
	 * @return a carta
	 */
	public Carta getCartaTopo() {
	   return this.cartas.remove(0);
	}
	/**
	 * Embaralha as cartas
	 */
	public void embaralhar() {
		Collections.shuffle(cartas);
	}
	/**
	 * Adiciona uma carta no baralho
	 * @param carta a carta para ser adicionada
	 */
	public void adicionaCarta(Carta carta) {
	   if(!this.cartas.contains(carta))
	      this.cartas.add(carta);
	}
}//fim da classe Baralho
