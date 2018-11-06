package model;
import java.util.*;

public class PlayerMovePOJO {

	private int deadCards;
	private int deckSize;
	private Collection<Card> hand;
	private DigimonCard digimonCard;
	private OptionCard supportCard;
	private int dp;
	private int victories;

	public int getDeadCards() {
		return this.deadCards;
	}

	/**
	 * 
	 * @param deadCards
	 */
	public void setDeadCards(int deadCards) {
		this.deadCards = deadCards;
	}

	public int getDeckSize() {
		return this.deckSize;
	}

	/**
	 * 
	 * @param deckSize
	 */
	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}

	public Collection<Card> getHand() {
		return this.hand;
	}

	/**
	 * 
	 * @param hand
	 */
	public void setHand(Collection<Card> hand) {
		this.hand = hand;
	}

	public DigimonCard getDigimonCard() {
		return this.digimonCard;
	}

	/**
	 * 
	 * @param digimonCard
	 */
	public void setDigimonCard(DigimonCard digimonCard) {
		this.digimonCard = digimonCard;
	}

	public OptionCard getSupportCard() {
		return this.supportCard;
	}

	/**
	 * 
	 * @param supportCard
	 */
	public void setSupportCard(OptionCard supportCard) {
		this.supportCard = supportCard;
	}

	public int getDp() {
		return this.dp;
	}

	/**
	 * 
	 * @param dp
	 */
	public void setDp(int dp) {
		this.dp = dp;
	}

	public int getVictories() {
		return this.victories;
	}

	/**
	 * 
	 * @param victories
	 */
	public void setVictories(int victories) {
		this.victories = victories;
	}

}