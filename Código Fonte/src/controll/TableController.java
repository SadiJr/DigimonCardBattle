package controll;
import model.Card;

import java.util.Collection;

import actor.ActorNetGames;
import actor.ActorPlayer;
import model.CardPOJO;
import model.Cemitery;
import model.DigimonCard;
import model.Player;
import model.PlayerMovePOJO;
import model.Table;

public class TableController {

	private Table table;
	private ActorNetGames network;
	private ActorPlayer player;
	
	public TableController() {
		table = new Table();
		network = new ActorNetGames(this);
		player = new ActorPlayer(this);
	}

	public Table getTable() {
		return this.table;
	}
	
	public void setTable(Table table) {
		this.table = table;
	}
	
	public ActorNetGames getNetwork() {
		return this.network;
	}
	
	public void setNetwork(ActorNetGames network) {
		this.network = network;
	}
	
	public ActorPlayer getPlayer() {
		return this.player;
	}
	
	public void setPlayer(ActorPlayer player) {
		this.player = player;
	}
	
	public void connect(String player, String server) {
		boolean connect = network.connect(player, server);
		if(connect)
			this.player.informMessage("Conexão estabelecida com sucesso");
	}

	public void disconnect() {
		network.disconnect();
	}

	public void startNewGame() {
		// TODO - implement TableController.startNewGame
		throw new UnsupportedOperationException();
	}

	public String getNameRemotePlayer() {
		return table.getRemotePlayer().getName();
	}

	public void informRemotePlayerName(String remotePlayerName) {
		player.informRemotePlayerName(getNameRemotePlayer());
	}

	public int start() {
		// TODO - implement TableController.start
		throw new UnsupportedOperationException();
	}

	public void treatMove(Table table) {
		// TODO - implement TableController.treatMove
		throw new UnsupportedOperationException();
	}


	public Table invertOrderPlayersReceivedTable(Table receivedTable) {
		Player aux = receivedTable.getLocalPlayer();
		receivedTable.setLocalPlayer(receivedTable.getRemotePlayer());
		receivedTable.setRemotePlayer(aux);
		return receivedTable;
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
		if(table.getLocalPlayer().getHand().isEmpty()) {
			player.informError("Você não possuí cartas na mão para descartar!");
			return;
		}
		
		if(table.existsDigimonCardOnSlot()) {
			table.addNewHand();
		} else {
			player.informError("Você não possuí mais nenhuma DigimonCard em seu deck!\nPelas regras do jogo, você perdeu!");
			table.getRemotePlayer().setVictories(3);
			player.informWinner(table.getNameRemotePlayer());
			network.sendMove(this.table);
			exit();
		}
	}

	public void downDigimonCard(String nameCard) {
		try {
			table.downDigimonCard(nameCard);
		} catch (Exception e) {
			player.informError(e.getMessage());
		}
	}

	public void digivolvePhase() {
		// TODO - implement TableController.digivolvePhase
		throw new UnsupportedOperationException();
	}

	public void sacrificeCard(String nameCard) {
		try {
			table.sacrificeCard(nameCard);
		} catch (Exception e) {
			player.informError(e.getMessage());
		}
	}

	public boolean isYourTurn() {
		if(table.getLocalPlayer().equals(table.getFirstPlayer()))
			return true;
		return false;
	}

	public Table invertOrderCemiteryReceivedTable(Table receivedTable) {
		Cemitery aux = receivedTable.getCemiteryLocalPlayer();
		receivedTable.setCemiteryLocalPlayer(receivedTable.getCemiteryRemotePlayer());
		receivedTable.setCemiteryRemotePlayer(aux);
		return receivedTable;
	}

	public PlayerMovePOJO createPOJORemotePlayer() {
		Player remotePlayer = table.getRemotePlayer();
		
		String name = remotePlayer.getName();
		int deckSize = remotePlayer.getDeck().getCards().size();
		int quantity = table.getCemiteryRemotePlayer().getQuantity();
		Collection<Card> hand = remotePlayer.getHand();
		DigimonCard digimonCard = remotePlayer.getDigimonCard();
		Card supportCard = remotePlayer.getSupportCard();
		int dp = remotePlayer.getDp();
		int victories = remotePlayer.getVictories();
		
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
		player.informError(error);
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

	public void choiceAttack(int attack) {
		table.getLocalPlayer().setAttackChoice(attack);
	}

	public void downSupportCard(String supportName) {
		try {
			table.downSupportCard(supportName);
		} catch (Exception e) {
			player.informError(e.getMessage());
		}
	}

	public void battle() {
		// TODO - implement TableController.battle
		throw new UnsupportedOperationException();
	}

	public void viewAttributes(String name) {
		// TODO - implement TableController.viewAttributes
		throw new UnsupportedOperationException();
	}

	public CardPOJO createCardPOJO(Card card) {
		CardPOJO pojo = null;
		
		String name = card.getName();
		String effect = card.getCardEffect().name();
		String description = card.getDescriptionEffect();
		
		if(table.isDigimonCard(card)) {
			DigimonCard digimon = (DigimonCard) card;
			int attack1 = digimon.getAttack1();
			int attack2 = digimon.getAttack2();
			int attack3 = digimon.getAttack3();
			int dp = digimon.getDp();
			int hp = digimon.getHp();
			char level = digimon.getLevel().name().charAt(0);
			int p = digimon.getP();
			String specialty = digimon.getSpecialty().name();
			pojo = new CardPOJO(hp, attack1, attack2, attack3, dp, p, specialty, level, name, 
					effect, description, false);
		} else {
			pojo = new CardPOJO(name, effect, description);
		}		
		return pojo;
	}

	public void exit() {
		// TODO - implement TableController.exit
		throw new UnsupportedOperationException();
	}

	public void sendMove(Table table) {
		// TODO - implement TableController.sendMove
		throw new UnsupportedOperationException();
	}

	public void notifyWinnerTurn(String name) {
		player.informMessage("O vencedor deste turno é " + name);
	}

	public String getNameLocalPlayer() {
		return table.getLocalPlayer().getName();
	}

}