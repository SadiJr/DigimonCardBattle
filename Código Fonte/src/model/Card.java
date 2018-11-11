package model;

import enums.Effect;

public abstract class Card {

	private String name;
	private Effect effect;
	private String path;
	
	public Card(String name, Effect effect, String path) {
		this.name = name;
		this.effect = effect;
		this.path = path;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Effect getCardEffect() {
		return this.effect;
	}

	public void setCardEffect(Effect effect) {
		this.effect = effect;
	}
	
	public String getDescriptionEffect() {
		return effect.getDescription();
	}

	public String getPathToImage() {
		return path;
	}

	public void setPathToImage(String path) {
		this.path = path;
	}

}