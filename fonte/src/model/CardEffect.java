package src.model;
public class CardEffect {

	private Effect effect;
	private String description;

	public Effect getEffect() {
		return this.effect;
	}

	/**
	 * 
	 * @param effect
	 */
	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}