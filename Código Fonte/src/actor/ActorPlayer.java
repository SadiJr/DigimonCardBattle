package actor;

import controll.TableController;
import model.CardPOJO;
import model.PlayerMovePOJO;
import view.AttributesScreen;
import view.DigimonScreen;
import view.UpdateScreen;

public class ActorPlayer {
	private TableController tableController;
	private DigimonScreen screen;
	private AttributesScreen attributesScreen;
	private UpdateScreen update;

	public ActorPlayer(TableController tableController) {
		this.tableController = tableController;
		screen = new DigimonScreen(this);
		attributesScreen = new AttributesScreen(this);
		update = new UpdateScreen(this);
	}

	public void connect() {
		String nameServer = screen.getNameServer();
		String namePlayer = screen.getNamePlayer();
		tableController.connect(namePlayer, nameServer);
	}

	public void disconnect() {
		tableController.disconnect();
	}

	public void informRemotePlayerName(String remotePlayerName) {
		screen.sendMessage("Iniciando partida com o adversário " + remotePlayerName);
	}

	public void start() {
		tableController.start();
	}

	public void informError(String error) {
		screen.informError(error);
	}

	public void informWinner(String winner) {
		screen.sendMessage("O vencedor do jogo é " + winner);
	}

	public void notifyPhase(String phase) {
		// TODO - implement ActorPlayer.notifyPhase
		throw new UnsupportedOperationException();
	}

	public void informTurn() {
		screen.sendMessage("É a sua vez!");
	}

	public void informQuit(String name) {
		screen.sendMessage("O adversário " + name + " é um covarde desistente que não conseguiu confrontá-lo.");
	}

	public void quit() {
		tableController.quit();
	}

	public void discardHand() {
		tableController.discardHand();
	}

	public void downDigimonCard(String nameCard) {
		tableController.downDigimonCard(nameCard);
	}

	public void informWaitMoveRemotePlayer(String namePlayer) {
		screen.sendMessage("Aguardando a jogada do adversário " + namePlayer);
	}

	public void enableButtonsDrawPhase() {
		attributesScreen.enableButtonsDrawPhase();
		//TODO habilitar botões da tela principal
	}

	public void updateInterface(PlayerMovePOJO remotePlayer, PlayerMovePOJO localPlayer) {
		// TODO - implement ActorPlayer.updateInterface
		throw new UnsupportedOperationException();
	}

	public void dissableButtonsDrawPhase() {
		attributesScreen.dissableButtonsDrawPhase();
	}

	public void enableButtonsDigivolvePhase() {
		attributesScreen.enableButtonSacrificeCard();
		// TODO - implement ActorPlayer.enableButtonsDigivolvePhase
		throw new UnsupportedOperationException();
	}

	public void dissableButtonsDigivolvePhase() {
		// TODO - implement ActorPlayer.dissableButtonsDigivolvePhase
		throw new UnsupportedOperationException();
	}

	public void dissableButtonSacrificeCard() {
		attributesScreen.dissableButtonSacrificeCard();
	}

	public void sacrificeCard(String name) {
		tableController.sacrificeCard(name);
	}

	public void updateCard(String name) {
		tableController.updateCard(name);
	}

	public void jumpPhase() {
		tableController.jumpPhase();
	}

	public void enableButtonsBattlePhase() {
		attributesScreen.enableButtonsAttack();
		// TODO - implement ActorPlayer.enableButtonsBattlePhase
		throw new UnsupportedOperationException();
	}
	
	public void attackChoice(int choice) {
		tableController.choiceAttack(choice);
	}

	public void dissableButtonsAttack() {
		attributesScreen.dissableButtonsAttack();
	}

	public void downSupportCard(String supportName) {
		tableController.downSupportCard(supportName);
	}

	public void dissableAllButtons() {
		attributesScreen.dissableAllButtons();
		// TODO - implement ActorPlayer.dissableAllButtons
		throw new UnsupportedOperationException();
	}

	public void viewAttributes(String name) {
		tableController.viewAttributes(name);
	}

	public AttributesScreen getAttributesScreen() {
		return this.attributesScreen;
	}

	public void setAttributesScreen(AttributesScreen attributesScreen) {
		this.attributesScreen = attributesScreen;
	}

	public void viewAttributes(CardPOJO pojo) {
		try {
			attributesScreen.showAttributes(pojo);
			attributesScreen.setVisible(true);
		} catch (Exception e) {
			informError(e.getMessage());
		}
	}

	public void notifyWinnerTurn(String name) {
		screen.sendMessage("O vencedor dessa rodada é " + name);
	}

	public void informMessage(String message) {
		screen.sendMessage(message);
	}

	public void init() {
		screen.setVisible(true);
	}
	
	public void viewAttributesDigimonCard(boolean opponent) {
		tableController.viewAttributesDigimonCard(opponent);
	}

	public void viewAttributesDigimonCard(CardPOJO pojo, boolean opponent) {
		try {
			if(opponent) {
				attributesScreen.showDigimonCardAttributesOpponent(pojo);
			}else {
				attributesScreen.showDigimonCardAttributes(pojo);
			}
		} catch (Exception e) {
			informError(e.getMessage());
		}
	}
	
	public void viewAttributesOptionCard(boolean opponent) {
		tableController.viewAttributesOptionCard(opponent);
	}

	public void viewAttributesOptionCard(CardPOJO pojo, boolean opponent) {
		try {
			if (opponent) {
				attributesScreen.showOptionCardAttributesOpponent(pojo);
			} else {
				attributesScreen.showOptionCardAttributes(pojo);
			}
		} catch (Exception e) {
			informError(e.getMessage());
		}		
	}

	public void viewOptionsUpdate() {
		PlayerMovePOJO createPOJOPlayer = tableController.createPOJOPlayer(tableController.getTable().getLocalPlayer());
		update.viewOptions(createPOJOPlayer.getHand());
	}
}