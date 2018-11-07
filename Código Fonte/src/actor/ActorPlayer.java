package actor;

import javax.swing.JOptionPane;

import controll.TableController;
import jdk.nashorn.internal.scripts.JO;
import model.CardPOJO;
import model.PlayerMovePOJO;
import view.AttributesScreen;
import view.DigimonScreen;

public class ActorPlayer {

	private TableController tableController;
	private DigimonScreen screen;
	private AttributesScreen attributesScreen;

	public ActorPlayer(TableController tableController) {
		this.tableController = tableController;
		screen = new DigimonScreen();
		attributesScreen = new AttributesScreen();
	}

	public void connect() {
		String nameServer = screen.getNameServer();
		String namePlayer = screen.getNamePlayer();
		tableController.connect(namePlayer, nameServer);
	}

	public String getNamePlayer() {
		// TODO - implement ActorPlayer.getNamePlayer
		throw new UnsupportedOperationException();
	}

	public String getNameServer() {
		// TODO - implement ActorPlayer.getNameServer
		throw new UnsupportedOperationException();
	}

	public void disconnect() {
		// TODO - implement ActorPlayer.disconnect
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param result
	 */
	public void informResultDesconection(int result) {
		// TODO - implement ActorPlayer.informResultDesconection
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param remotePlayerName
	 */
	public void informRemotePlayerName(String remotePlayerName) {
		// TODO - implement ActorPlayer.informRemotePlayerName
		throw new UnsupportedOperationException();
	}

	public void start() {
		// TODO - implement ActorPlayer.start
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param error
	 */
	public void informError(String error) {
		screen.informError(error);
	}

	/**
	 * 
	 * @param result
	 */
	public String informResultStart(int result) {
		// TODO - implement ActorPlayer.informResultStart
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param winner
	 */
	public void informWinner(String winner) {
		// TODO - implement ActorPlayer.informWinner
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param phase
	 */
	public void notifyPhase(String phase) {
		// TODO - implement ActorPlayer.notifyPhase
		throw new UnsupportedOperationException();
	}

	public void informTurn() {
		screen.sendMessage("É a sua vez!");
	}

	/**
	 * 
	 * @param name
	 */
	public void informQuit(String name) {
		// TODO - implement ActorPlayer.informQuit
		throw new UnsupportedOperationException();
	}

	public void quit() {
		// TODO - implement ActorPlayer.quit
		throw new UnsupportedOperationException();
	}

	public void discardHand() {
		// TODO - implement ActorPlayer.discardHand
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nameCard
	 */
	public void downDigimonCard(String nameCard) {
		tableController.downDigimonCard(nameCard);
	}

	/**
	 * 
	 * @param namePlayer
	 */
	public void informWaitMoveRemotePlayer(String namePlayer) {
		// TODO - implement ActorPlayer.informWaitMoveRemotePlayer
		throw new UnsupportedOperationException();
	}

	public void enableButtonsDrawPhase() {
		// TODO - implement ActorPlayer.enableButtonsDrawPhase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param remotePlayer
	 * @param localPlayer
	 */
	public void updateInterface(PlayerMovePOJO remotePlayer, PlayerMovePOJO localPlayer) {
		// TODO - implement ActorPlayer.updateInterface
		throw new UnsupportedOperationException();
	}

	public void dissableButtonsDrawPhase() {
		// TODO - implement ActorPlayer.dissableButtonsDrawPhase
		throw new UnsupportedOperationException();
	}

	public void enableButtonsDigivolvePhase() {
		// TODO - implement ActorPlayer.enableButtonsDigivolvePhase
		throw new UnsupportedOperationException();
	}

	public void dissableButtonsDigivolvePhase() {
		// TODO - implement ActorPlayer.dissableButtonsDigivolvePhase
		throw new UnsupportedOperationException();
	}

	public void dissableButtonSacrificeCard() {
		// TODO - implement ActorPlayer.dissableButtonSacrificeCard
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void sacrificeCard(String name) {
		// TODO - implement ActorPlayer.sacrificeCard
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void updateCard(String name) {
		// TODO - implement ActorPlayer.updateCard
		throw new UnsupportedOperationException();
	}

	public void jumpPhase() {
		// TODO - implement ActorPlayer.jumpPhase
		throw new UnsupportedOperationException();
	}

	public void enableButtonsBattlePhase() {
		// TODO - implement ActorPlayer.enableButtonsBattlePhase
		throw new UnsupportedOperationException();
	}

	public void attack1() {
		// TODO - implement ActorPlayer.attack1
		throw new UnsupportedOperationException();
	}

	public void attack2() {
		// TODO - implement ActorPlayer.attack2
		throw new UnsupportedOperationException();
	}

	public void attack3() {
		// TODO - implement ActorPlayer.attack3
		throw new UnsupportedOperationException();
	}

	public void dissableButtonsAttack() {
		// TODO - implement ActorPlayer.dissableButtonsAttack
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param supportName
	 */
	public void downSupportCard(String supportName) {
		// TODO - implement ActorPlayer.downSupportCard
		throw new UnsupportedOperationException();
	}

	public void dissableAllButtons() {
		// TODO - implement ActorPlayer.dissableAllButtons
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void viewAttributes(String name) {
		// TODO - implement ActorPlayer.viewAttributes
		throw new UnsupportedOperationException();
	}

	public AttributesScreen getAttributesScreen() {
		return this.attributesScreen;
	}

	/**
	 * 
	 * @param attributesScreen
	 */
	public void setAttributesScreen(AttributesScreen attributesScreen) {
		this.attributesScreen = attributesScreen;
	}

	/**
	 * 
	 * @param pojo
	 */
	public void viewAttributes(CardPOJO pojo) {
		// TODO - implement ActorPlayer.viewAttributes
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void notifyWinnerTurn(String name) {
		// TODO - implement ActorPlayer.notifyWinnerTurn
		throw new UnsupportedOperationException();
	}

	public void informMessage(String message) {
		screen.sendMessage(message);
	}

}