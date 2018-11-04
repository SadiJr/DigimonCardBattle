package src.model;

import java.util.Collection;

public class Player {

	private String name;
	private Deck deck;
	private Collection<Card> hand;
	private int victories;
	private int dp;
	private DigimonCard digimonCard;
	private Card supportCard;
	private int attackChoice;
	
	public Player(String name) {
		this.name = name;
	}

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

	public Deck getDeck() {
		return this.deck;
	}

	/**
	 * 
	 * @param deck
	 */
	public void setDeck(Deck deck) {
		this.deck = deck;
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

	public Card getSupportCard() {
		return this.supportCard;
	}

	/**
	 * 
	 * @param supportCard
	 */
	public void setSupportCard(Card supportCard) {
		this.supportCard = supportCard;
	}

	/**
	 * 
	 * @param card
	 */
	public void discardCard(Card card) {
		// TODO - implement Player.discardCard
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param digimoCard
	 */
	public void setDigimonCardIrregularLevelC(DigimonCard digimoCard) {
		// TODO - implement Player.setDigimonCardIrregularLevelC
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param digimonCard
	 */
	public void setDigimonCardIrregularLevelU(DigimonCard digimonCard) {
		// TODO - implement Player.setDigimonCardIrregularLevelU
		throw new UnsupportedOperationException();
	}

	public int getAttackChoice() {
		return this.attackChoice;
	}

	/**
	 * 
	 * @param attackChoice
	 */
	public void setAttackChoice(int attackChoice) {
		this.attackChoice = attackChoice;
	}

}