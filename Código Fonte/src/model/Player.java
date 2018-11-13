package model;

import java.util.ArrayList;
import java.util.Collection;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Player implements Jogada {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Deck deck;
	private Collection<Card> hand;
	private int victories;
	private int dp;
	private DigimonCard digimonCard;
	private Card supportCard;
	private int attackChoice;
	private boolean first;
	
	public Player(String name) {
		this.name = name;
		deck = new Deck();
		hand = new ArrayList<>();
	}
		
	public Player(String name, int id) {
		this.id = id;
		this.name = name;
		deck = new Deck();
		hand = new ArrayList<>();
		if(id == 1) {
			first = true;
		} else {
			first = false;
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Deck getDeck() {
		return this.deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Collection<Card> getHand() {
		return this.hand;
	}

	public void setHand(Collection<Card> hand) {
		this.hand = hand;
	}

	public int getVictories() {
		return this.victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	public int getDp() {
		return this.dp;
	}

	public void setDp(int dp) {
		this.dp = dp;
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

	public void discardCard(Card card) {
		hand.remove(card);
	}

	public void setDigimonCardIrregularLevelC(DigimonCard digimonCard) {
		digimonCard.setAttack1((int) (digimonCard.getAttack1() * 0.50));
		digimonCard.setAttack1((int) (digimonCard.getAttack2() * 0.50));
		digimonCard.setAttack1((int) (digimonCard.getAttack3() * 0.50));
		setDigimonCard(digimonCard);
	}

	public void setDigimonCardIrregularLevelU(DigimonCard digimonCard) {
		digimonCard.setAttack1((int) (digimonCard.getAttack1() * 0.25));
		digimonCard.setAttack1((int) (digimonCard.getAttack2() * 0.25));
		digimonCard.setAttack1((int) (digimonCard.getAttack3() * 0.25));
		setDigimonCard(digimonCard);
	}

	public int getAttackChoice() {
		return this.attackChoice;
	}

	public void setAttackChoice(int attackChoice) {
		this.attackChoice = attackChoice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean isFirst) {
		this.first = isFirst;
	}
}