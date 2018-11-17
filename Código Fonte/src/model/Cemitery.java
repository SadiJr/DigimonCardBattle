package model;

import java.util.ArrayList;
import java.util.Collection;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Cemitery implements Jogada {
	private static final long serialVersionUID = 1L;
	private Collection<Card> deadCards;
	private int quantity;
	
	public Cemitery() {
		this.deadCards = new ArrayList<>();
		this.quantity = 0;
	}
	
	public Collection<Card> getDeadCards() {
		return this.deadCards;
	}

	public void setDeadCards(Collection<Card> deadCards) {
		this.deadCards = deadCards;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addCard(Card deadCard) {
		this.deadCards.add(deadCard);
		setQuantity(getQuantity() + 1);
	}

}