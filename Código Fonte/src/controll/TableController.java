package controll;
import javax.smartcardio.Card;

import actor.ActorNetGames;
import actor.ActorPlayer;
import model.CardPOJO;
import model.PlayerMovePOJO;
import model.Table;

public class TableController {

	private Table table;
	private ActorNetGames network;
	private ActorPlayer player;

	public int connect() {
		// TODO - implement TableController.connect
		throw new UnsupportedOperationException();
	}

	public int disconnect() {
		// TODO - implement TableController.disconnect
		throw new UnsupportedOperationException();
	}

	public void startNewGame() {
		// TODO - implement TableController.startNewGame
		throw new UnsupportedOperationException();
	}

	public String getNameRemotePlayer() {
		// TODO - implement TableController.getNameRemotePlayer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param remotePlayerName
	 */
	public void informRemotePlayerName(String remotePlayerName) {
		// TODO - implement TableController.informRemotePlayerName
		throw new UnsupportedOperationException();
	}

	public int start() {
		// TODO - implement TableController.start
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param table
	 */
	public void treatMove(Table table) {
		// TODO - implement TableController.treatMove
		throw new UnsupportedOperationException();
	}

	public Table getTable() {
		return this.table;
	}

	/**
	 * 
	 * @param table
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	public ActorNetGames getNetwork() {
		return this.network;
	}

	/**
	 * 
	 * @param network
	 */
	public void setNetwork(ActorNetGames network) {
		this.network = network;
	}

	public ActorPlayer getPlayer() {
		return this.player;
	}

	/**
	 * 
	 * @param player
	 */
	public void setPlayer(ActorPlayer player) {
		this.player = player;
	}

	/**
	 * 
	 * @param receivedTable
	 */
	public Table invertOrderPlayersReceivedTable(Table receivedTable) {
		// TODO - implement TableController.invertOrderPlayersReceivedTable
		throw new UnsupportedOperationException();
	}

	public void drawPhase() {
		// TODO - implement TableController.drawPhase
		throw new UnsupportedOperationException();
	}

	public String quit() {
		// TODO - implement TableController.quit
		throw new UnsupportedOperationException();
	}

	public void discardHand() {
		// TODO - implement TableController.discardHand
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nameCard
	 */
	public void downDigimonCard(String nameCard) {
		// TODO - implement TableController.downDigimonCard
		throw new UnsupportedOperationException();
	}

	public void digivolvePhase() {
		// TODO - implement TableController.digivolvePhase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nameCard
	 */
	public void sacrificeCard(String nameCard) {
		// TODO - implement TableController.sacrificeCard
		throw new UnsupportedOperationException();
	}

	public boolean isYourTurn() {
		// TODO - implement TableController.isYourTurn
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param receivedTable
	 */
	public Table invertOrderCemiteryReceivedTable(Table receivedTable) {
		// TODO - implement TableController.invertOrderCemiteryReceivedTable
		throw new UnsupportedOperationException();
	}

	public PlayerMovePOJO createPOJORemotePlayer() {
		// TODO - implement TableController.createPOJORemotePlayer
		throw new UnsupportedOperationException();
	}

	public PlayerMovePOJO createPOJOLocalPlayer() {
		// TODO - implement TableController.createPOJOLocalPlayer
		throw new UnsupportedOperationException();
	}

	public void updateInterface() {
		// TODO - implement TableController.updateInterface
		throw new UnsupportedOperationException();
	}

	public void informMessage(String message) {
		player.informMessage(message);
	}
	
	public void informError(String error) {
		// TODO - implement TableController.informError
		throw new UnsupportedOperationException();
	}

	public void dissableButtonsDrawPhase() {
		// TODO - implement TableController.dissableButtonsDrawPhase
		throw new UnsupportedOperationException();
	}

	public void dissableButtonsDigivolvePhase() {
		// TODO - implement TableController.dissableButtonsDigivolvePhase
		throw new UnsupportedOperationException();
	}

	public void dissableButtonSacrificeCard() {
		// TODO - implement TableController.dissableButtonSacrificeCard
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void updateCard(String name) {
		// TODO - implement TableController.updateCard
		throw new UnsupportedOperationException();
	}

	public void battlePhase() {
		// TODO - implement TableController.battlePhase
		throw new UnsupportedOperationException();
	}

	public void jumpPhase() {
		// TODO - implement TableController.jumpPhase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attack
	 */
	public void choiceAttack(int attack) {
		// TODO - implement TableController.choiceAttack
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param supportName
	 */
	public void downSupportCard(String supportName) {
		// TODO - implement TableController.downSupportCard
		throw new UnsupportedOperationException();
	}

	public void battle() {
		// TODO - implement TableController.battle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void viewAttributes(String name) {
		// TODO - implement TableController.viewAttributes
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param card
	 */
	public CardPOJO createCardPOJO(Card card) {
		// TODO - implement TableController.createCardPOJO
		throw new UnsupportedOperationException();
	}

	public void exit() {
		// TODO - implement TableController.exit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param table
	 */
	public void sendMove(Table table) {
		// TODO - implement TableController.sendMove
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void notifyWinnerTurn(String name) {
		// TODO - implement TableController.notifyWinnerTurn
		throw new UnsupportedOperationException();
	}

	public String getNameLocalPlayer() {
		return table.getLocalPlayer().getName();
	}

}