package model;

import enums.Effect;

public abstract class Card {

	private String name;
	private Effect cardEffect;
	
	public Card(String name, Effect cardEffect) {
		this.name = name;
		this.cardEffect = cardEffect;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Effect getCardEffect() {
		return this.cardEffect;
	}

	public void setCardEffect(Effect cardEffect) {
		this.cardEffect = cardEffect;
	}
	
	public String getDescriptionEffect() {
		return cardEffect.getDescription();
	}

}