package src.model;

import java.util.Collection;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Table implements Jogada {

	private Deck deck;
	private Player localPlayer;
	private Player remotePlayer;
	private boolean gameInProggress;
	private boolean connected;
	private Player firstPlayer;
	private Phase phase;
	private int turns;
	private Cemitery cemiteryLocalPlayer;
	private Cemitery cemiteryRemotePlayer;

	public Deck getDeck() {
		return this.deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Player getLocalPlayer() {
		return this.localPlayer;
	}

	public void setLocalPlayer(Player localPlayer) {
		this.localPlayer = localPlayer;
	}

	public Player getRemotePlayer() {
		return this.remotePlayer;
	}

	public void setRemotePlayer(Player remotePlayer) {
		this.remotePlayer = remotePlayer;
	}

	public boolean isGameInProggress() {
		return this.gameInProggress;
	}

	public void setGameInProggress(boolean gameInProggress) {
		this.gameInProggress = gameInProggress;
	}

	public boolean isConnected() {
		return this.connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public void createDeck() {
		// TODO - implement Table.createDeck
		throw new UnsupportedOperationException();
	}

	public void createLocalPlayer(String name) {
		this.localPlayer = new Player(name);
	}

	public void distributeCards() {
		// TODO - implement Table.distributeCards
		throw new UnsupportedOperationException();
	}

	public void takeAction() {
		// TODO - implement Table.takeAction
		throw new UnsupportedOperationException();
	}

	public Phase getPhase() {
		return this.phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public int getTurns() {
		return this.turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public String getNameRemotePlayer() {
		// TODO - implement Table.getNameRemotePlayer
		throw new UnsupportedOperationException();
	}

	public Player verifyWinner() {
		if(localPlayer.getVictories() == 3)
			return localPlayer;
		if(remotePlayer.getVictories() == 3)
			return remotePlayer;
		return null;
	}

	/**
	 * 
	 * @param name
	 */
	public void createRemotePlayer(String name) {
		this.remotePlayer = new Player(name);
	}

	public void discardHand() {
		Collection<Card> hand = localPlayer.getHand();
		for(Card card : hand) {
			cemiteryLocalPlayer.addCard(card);
		}
		localPlayer.setHand(null);
		addNewHand();
	}

	/**
	 * 
	 * @param nameCard
	 */
	public void downDigimonCard(String nameCard) {
		// TODO - implement Table.downDigimonCard
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cardName
	 */
	public void sacrificeCard(String cardName) {
		// TODO - implement Table.sacrificeCard
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param card
	 */
	public boolean isDigimonCard(Card card) {
		// TODO - implement Table.isDigimonCard
		throw new UnsupportedOperationException();
	}

	public Cemitery getCemiteryLocalPlayer() {
		return this.cemiteryLocalPlayer;
	}

	/**
	 * 
	 * @param cemiteryLocalPlayer
	 */
	public void setCemiteryLocalPlayer(Cemitery cemiteryLocalPlayer) {
		this.cemiteryLocalPlayer = cemiteryLocalPlayer;
	}

	public Cemitery getCemiteryRemotePlayer() {
		return this.cemiteryRemotePlayer;
	}

	/**
	 * 
	 * @param cemiteryRemotePlayer
	 */
	public void setCemiteryRemotePlayer(Cemitery cemiteryRemotePlayer) {
		this.cemiteryRemotePlayer = cemiteryRemotePlayer;
	}

	public Player getFirstPlayer() {
		return this.firstPlayer;
	}

	/**
	 * 
	 * @param firstPlayer
	 */
	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public void addNewHand() {
		Deck deck2 = localPlayer.getDeck();
		
		// TODO - implement Table.addNewHand
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public Card getCardByName(String name) {
		// TODO - implement Table.getCardByName
		throw new UnsupportedOperationException();
	}

	public boolean existsDigimonCardOnSlot() {
		// TODO - implement Table.existsDigimonCardOnSlot
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void updateCard(String name) {
		// TODO - implement Table.updateCard
		throw new UnsupportedOperationException();
	}

	public boolean isDpEnough() {
		// TODO - implement Table.isDpEnough
		throw new UnsupportedOperationException();
	}

	public DigimonCard evolutionPossible() {
		// TODO - implement Table.evolutionPossible
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param card
	 */
	public void removeCardOfHand(Card card) {
		// TODO - implement Table.removeCardOfHand
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param digimonCard
	 */
	public void downDigimonCard(DigimonCard digimonCard) {
		// TODO - implement Table.downDigimonCard
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attack
	 */
	public void choiceAttack(int attack) {
		// TODO - implement Table.choiceAttack
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param supportName
	 */
	public void downSupportCard(String supportName) {
		// TODO - implement Table.downSupportCard
		throw new UnsupportedOperationException();
	}

	public void battle() {
		// TODO - implement Table.battle
		throw new UnsupportedOperationException();
	}

	public void createHandLocalPlayer() {
		// TODO - implement Table.createHandLocalPlayer
		throw new UnsupportedOperationException();
	}

	public int getSizeHandLocalPlayer() {
		// TODO - implement Table.getSizeHandLocalPlayer
		throw new UnsupportedOperationException();
	}

	public void addMissingCards() {
		// TODO - implement Table.addMissingCards
		throw new UnsupportedOperationException();
	}

	public boolean existsDigimonCardInHand() {
		for (Card card : localPlayer.getHand()) {
			if(card instanceof DigimonCard)
				return true;
		} 
		return false;
	}

	public void supportCardEffect() {
		// TODO - implement Table.supportCardEffect
		throw new UnsupportedOperationException();
	}

	public void battleTurn() {
		// TODO - implement Table.battleTurn
		throw new UnsupportedOperationException();
	}
	
	public boolean attack(Player playerAttacked, int attack) {
		// TODO - implement Table.attack
		throw new UnsupportedOperationException();
	}

	public void addCardToCemiteryByPlayer(Player player, Card card) {
		if(player.equals(localPlayer)) {
			cemiteryLocalPlayer.addCard(card);
		} else {
			cemiteryRemotePlayer.addCard(card);
		}
	}
}