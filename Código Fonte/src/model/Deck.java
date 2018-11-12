package model;

import java.util.ArrayList;
import java.util.Collection;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Deck implements Jogada {
	private Collection<Card> cards;

	public Deck() {
		this.cards = new ArrayList<>();
	}

	public Collection<Card> getCards() {
		return this.cards;
	}
	
	public void setCards(Collection<Card> cards) {
		this.cards = cards;
	}

}