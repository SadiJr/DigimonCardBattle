package model;

import java.util.ArrayList;
import java.util.Collection;

public class Cemitery {
	
	public Cemitery() {
		this.deadCards = new ArrayList<>();
		this.quantity = 0;
	}

	private Collection<Card> deadCards;
	private int quantity;

	public Collection<Card> getDeadCards() {
		return this.deadCards;
	}

	/**
	 * 
	 * @param deadCards
	 */
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