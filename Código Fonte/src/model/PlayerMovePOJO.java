package model;
import java.util.*;

public class PlayerMovePOJO {
	private String name;
	private int deadCards;
	private int deckSize;
	private Collection<Card> hand;
	private DigimonCard digimonCard;
	private Card supportCard;
	private int dp;
	private int victories;
	
	public PlayerMovePOJO(String name, int deadCards, int deckSize, Collection<Card> hand, DigimonCard digimon, 
			Card support, int dp, int victories) {
		this.setName(name);
		this.deadCards = deadCards;
		this.deckSize = deckSize;
		this.hand = hand;
		this.digimonCard = digimon;
		this.supportCard = support;
		this.dp = dp;
		this.victories = victories;
	}

	public int getDeadCards() {
		return this.deadCards;
	}

	public void setDeadCards(int deadCards) {
		this.deadCards = deadCards;
	}

	public int getDeckSize() {
		return this.deckSize;
	}

	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}

	public Collection<Card> getHand() {
		return this.hand;
	}

	public void setHand(Collection<Card> hand) {
		this.hand = hand;
	}

	public DigimonCard getDigimonCard() {
		return this.digimonCard;
	}

	public void setDigimonCard(DigimonCard digimonCard) {
		this.digimonCard = digimonCard;
	}

	public Card getSupportCard() {
		return this.supportCard;
	}

	public void setSupportCard(Card supportCard) {
		this.supportCard = supportCard;
	}

	public int getDp() {
		return this.dp;
	}

	public void setDp(int dp) {
		this.dp = dp;
	}

	public int getVictories() {
		return this.victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}