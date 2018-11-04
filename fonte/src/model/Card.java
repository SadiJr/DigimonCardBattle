package src.model;
public class Card {

	private String name;
	private CardEffect cardEffect;

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public CardEffect getCardEffect() {
		return this.cardEffect;
	}

	/**
	 * 
	 * @param cardEffect
	 */
	public void setCardEffect(CardEffect cardEffect) {
		this.cardEffect = cardEffect;
	}

}