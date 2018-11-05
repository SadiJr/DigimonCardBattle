package src.model;
public class Cemitery {

	private Card deadCards;
	private int quantity;

	public Card getDeadCards() {
		return this.deadCards;
	}

	/**
	 * 
	 * @param deadCards
	 */
	public void setDeadCards(Card deadCards) {
		this.deadCards = deadCards;
	}

	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * @param deadCard
	 */
	public void addCard(Card deadCard) {
		// TODO - implement Cemitery.addCard
		throw new UnsupportedOperationException();
	}

}