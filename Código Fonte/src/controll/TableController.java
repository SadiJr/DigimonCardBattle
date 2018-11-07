package controll;
import model.Card;

import java.util.Collection;

import actor.ActorNetGames;
import actor.ActorPlayer;
import enums.Phase;
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
		table = invertOrderPlayersReceivedTable(table);
		table = invertOrderCemiteryReceivedTable(table);
		setTable(table);
		Player winner = table.verifyWinner();
		if(winner != null) {
			player.informWinner(winner.getName());
			exit();
		} else {
			if(table.getPhase().equals(Phase.QUIT)) {
				player.informMessage("O jogador " + table.getNameRemotePlayer() + " é um covarde desistente!");
				exit();
			} else {
				int turns = table.getTurns();
				if(turns < 2) {
					table.setTurns(turns + 1);
					drawPhase();
				} else if(turns == 2) {
					battle();
				} else {
					player.informError("Ocorreu um erro durante o tratamento do lance!");
					network.finalizarPartidaComErro("Ocorreu um erro durante o tratamento do lance!");
					exit();
				}
			}
		}
	}

	public Table invertOrderPlayersReceivedTable(Table receivedTable) {
		Player aux = receivedTable.getLocalPlayer();
		receivedTable.setLocalPlayer(receivedTable.getRemotePlayer());
		receivedTable.setRemotePlayer(aux);
		return receivedTable;
	}

	public void drawPhase() {
		table.setPhase(Phase.DRAW_PHASE);
		player.notifyPhase(Phase.DRAW_PHASE.getDescription());
		updateInterface();
		table.createHandLocalPlayer();
		if(table.existsDigimonCardInHand() || table.existsDigimonCardOnSlot()) {
			player.informTurn();
			player.enableButtonsDrawPhase();
		} else {
			player.informMessage("Você não possuí nenhuma DigimonCard em sua mão ou no campo de batalha! "
					+ "Favor, descartar sua mão para tentar obter uma DigimonCard e prosseguir o jogo");			
		}
	}

	public void quit() {
		if(table.isGameInProggress()) {
			table.setGameInProggress(false);
			table.setPhase(Phase.QUIT);
			network.sendMove(this.table);
			player.informMessage("Desistência realizada com sucesso! Parabéns, você é um perdedor!");
			exit();
		} else {
			player.informMessage("Sua covardia já o fez desistir!");
		}
	}

	public void discardHand() {
		if(table.getLocalPlayer().getHand().isEmpty()) {
			player.informError("Você não possuí cartas na mão para descartar!");
			return;
		}
		
		if(table.existsDigimonCardOnSlot() || table.existsDigimonCardInDeck()) {
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
			digivolvePhase();
		} catch (Exception e) {
			player.informError(e.getMessage());
		}
	}

	public void digivolvePhase() {
		table.setPhase(Phase.DIGIVOLVE_PHASE);
		player.notifyPhase(Phase.DIGIVOLVE_PHASE.getDescription());
		updateInterface();
		player.informTurn();
		player.enableButtonsDigivolvePhase();
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
	
	public PlayerMovePOJO createPOJOPlayer(Player player) {
		String name = player.getName();
		int size = player.getDeck().getCards().size();
		int cemiterySize = table.getLocalPlayer().equals(player) ? table.getCemiteryLocalPlayer().getDeadCards().size() 
				: table.getCemiteryRemotePlayer().getDeadCards().size();
		Collection<Card> hand = player.getHand();
		DigimonCard digimonCard = player.getDigimonCard();
		Card supportCard = player.getSupportCard();
		int dp = player.getDp();
		int victories = player.getVictories();
		
		return new PlayerMovePOJO(name, cemiterySize, size, hand, digimonCard, supportCard, dp, victories);
	}

	public void updateInterface() {
		PlayerMovePOJO pojoLocalPlayer = createPOJOPlayer(table.getLocalPlayer());
		PlayerMovePOJO pojoRemotePlayer = createPOJOPlayer(table.getRemotePlayer());
		player.updateInterface(pojoRemotePlayer, pojoLocalPlayer);
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
		Phase phase = table.getPhase();
		switch(phase) {
		case DRAW_PHASE:
			if(table.existsDigimonCardOnSlot())
				digivolvePhase();
			else
				player.informError("Você precisa ter uma DigimonCard em campo para realizar essa operação!");
			break;
		
		case DIGIVOLVE_PHASE:
			battlePhase();
			break;

		default:
			player.informError("Essa fase não pode ser pulada!");
		
		}
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
		Card card = table.getCardByName(name);
		if(card == null) {
			player.informError("Um erro inesperado ocorreu, revise o código!");
			return;
		}
		player.viewAttributes(createCardPOJO(card));
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
					effect, description);
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
		player.dissableAllButtons();
		network.sendMove(table);
	}

	public void notifyWinnerTurn(String name) {
		player.informMessage("O vencedor deste turno é " + name);
	}

	public String getNameLocalPlayer() {
		return table.getLocalPlayer().getName();
	}
}