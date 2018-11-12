package controll;
import java.util.ArrayList;

import actor.ActorNetGames;
import actor.ActorPlayer;
import enums.Phase;
import model.Card;
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
		table.setLocalPlayer(new Player(player));
		if(connect)
			this.player.informMessage("Conexão estabelecida com sucesso");
	}

	public void disconnect() {
		network.disconnect();
	}

	public void startNewGame(Integer posicao) {
		try {
			String nameRemotePlayer = network.getNameRemotePlayer();
			player.informRemotePlayerName(nameRemotePlayer);
			table.getLocalPlayer().setId(posicao);
			table.setRemotePlayer(new Player(nameRemotePlayer, posicao == 1 ? 2 : 1));
			table.createDeck();
			table.distributeCards();
			table.setPhase(Phase.START_GAME);
			table.setGameInProggress(true);
			table.setFirstPlayer(table.getRemotePlayer());
			network.sendMove(table);
			player.informWaitMoveRemotePlayer(nameRemotePlayer);
		} catch (Exception e) {
			player.informError(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getNameRemotePlayer() {
		return table.getRemotePlayer().getName();
	}

	public void informRemotePlayerName(String remotePlayerName) {
		player.informRemotePlayerName(getNameRemotePlayer());
	}

	public void start() {
		network.sendRequestMove();
	}
	
	public void init() {
		player.init();;
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
				player.informMessage("O jogador " + getNameRemotePlayer() + " é um covarde desistente!");
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
			player.enableButtonsDrawPhase();
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
			player.informWinner(getNameRemotePlayer());
			network.sendMove(this.table);
			exit();
		}
	}

	public void downDigimonCard(String nameCard) {
		try {
			table.downDigimonCard(nameCard);
			player.dissableButtonsDrawPhase();
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
	}

	public void sacrificeCard(String nameCard) {
		try {
			table.sacrificeCard(nameCard);
			player.dissableButtonSacrificeCard();
			updateInterface();
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
		ArrayList<Card> hand = (ArrayList<Card>) player.getHand();
		ArrayList<CardPOJO> hand2 = new ArrayList<>();
		for(int i = 0; i < hand.size(); i++) {
			Card card = hand.get(i);
			if(card == null) {
				hand2.add(null);
			} else {
				CardPOJO createCardPOJO = createCardPOJO(card);
				hand2.add(createCardPOJO);
			}
		}
		CardPOJO digimonCard = null;
		CardPOJO supportCard = null;
		if(player.getDigimonCard() != null) {
			digimonCard = createCardPOJO(player.getDigimonCard());
		}
		if(player.getSupportCard() != null) {
			supportCard = createCardPOJO(player.getSupportCard());
		}
		int dp = player.getDp();
		int victories = player.getVictories();
		
		return new PlayerMovePOJO(name, cemiterySize, size, hand2, digimonCard, supportCard, dp, victories);
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

	public void updateCard(String name) {
		try {
			table.updateCard(name);
			player.dissableButtonsDigivolvePhase();
			battlePhase();
		} catch (Exception e) {
			player.informError(e.getMessage());
		}
	}

	public void battlePhase() {
		table.setPhase(Phase.BATTLE_PHASE);
		player.notifyPhase(Phase.BATTLE_PHASE.getDescription());
		updateInterface();
		player.informTurn();
		player.enableButtonsBattlePhase();
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
			break;
		}
	}

	public void choiceAttack(int attack) {
		table.getLocalPlayer().setAttackChoice(attack);
		player.dissableButtonsAttack();
	}

	public void downSupportCard(String supportName) {
		try {
			table.downSupportCard(supportName);
			player.dissableAllButtons();
			network.sendMove(table);
			int turns = table.getTurns();
			if(turns < 2) {
				player.informWaitMoveRemotePlayer(getNameRemotePlayer());
			} else if(turns == 2) {
				battle();
			} else {
				throw new Exception("Erro de programação na linha 208 na classe TabbeController! Possível erro de lógica ou de implementação");
			}
		} catch (Exception e) {
			player.informError(e.getMessage());
		}
	}

	public void battle() {
		Player first = table.getLocalPlayer().equals(table.getFirstPlayer()) ? table.getLocalPlayer() : table.getRemotePlayer();
		Player second= table.getLocalPlayer().equals(table.getFirstPlayer()) ? table.getRemotePlayer() : table.getLocalPlayer();
		DigimonCard aux1 = DigimonCard.copy(first.getDigimonCard());
		DigimonCard aux2 = DigimonCard.copy(second.getDigimonCard());
		
		try {
			table.supportCardEffect(first);
			table.supportCardEffect(second);
			table.battleTurn();
		} catch (Exception e) {
			player.informError(e.getMessage());
		}
		
		if(first.getDigimonCard().getHp() <= 0 || second.getDigimonCard().getHp() <= 0) {
			for(Player player : table.getListPlayers()) {
				if(player.getDigimonCard().getHp() <= 0) {
					this.player.notifyWinnerTurn(player.equals(table.getLocalPlayer()) ? table.getRemotePlayer().getName() 
							: table.getLocalPlayer().getName());
					table.addCardToCemiteryByPlayer(player, player.getDigimonCard());
					player.setDigimonCard(null);
					Player winner = player.equals(table.getLocalPlayer()) ? table.getLocalPlayer() : table.getRemotePlayer();
					winner.setVictories(winner.getVictories() + 1);
					return;
				}
			}

			if(table.verifyWinner() != null) {
				this.player.informWinner(table.verifyWinner().getName());
				exit();
			}
			
		} else {
				int hp = first.getDigimonCard().getHp();
				first.setDigimonCard(aux1);
				first.getDigimonCard().setHp(hp);
				
				int hp2 = second.getDigimonCard().getHp();
				second.setDigimonCard(aux2);
				second.getDigimonCard().setHp(hp2);
		}
		
		if(table.getLocalPlayer().equals(table.getFirstPlayer())) {
			table.setTurns(1);
			drawPhase();
		} else {
			sendMove(this.table);
			player.informWaitMoveRemotePlayer(getNameRemotePlayer());
		}
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
		String path = card.getPathToImage();
		String phase = table.getPhase().name();
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
					"Não há", "Não Há", path, phase);
		} else {
			String effect = card.getCardEffect().name();
			String description = card.getDescriptionEffect();
			pojo = new CardPOJO(name, effect, description, path, phase);
		}		
		return pojo;
	}

	public void exit() {
		String name = table.getLocalPlayer().getName();
		Integer id = table.getLocalPlayer().getId();
		table = null;
		table = new Table();
		table.setLocalPlayer(new Player(name, id));
		PlayerMovePOJO createPOJOPlayer = createPOJOPlayer(table.getLocalPlayer());
		player.updateInterface(null, createPOJOPlayer);
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

	public void viewAttributesDigimonCard(boolean opponent) {
		if(opponent) {
			DigimonCard digimonCard = table.getRemotePlayer().getDigimonCard();
			if(digimonCard == null) {
				player.informError("O adversário não possuí nenhuma DigimonCard em campo de batalha!");
			} else {
				CardPOJO createCardPOJO = createCardPOJO(digimonCard);
				player.viewAttributesDigimonCard(createCardPOJO, opponent);
			}
		} else {
			DigimonCard digimonCard = table.getLocalPlayer().getDigimonCard();
			if(digimonCard == null) {
				player.informError("Você não possuí nenhuma DigimonCard em campo de batalha!");
			} else {
				CardPOJO createCardPOJO = createCardPOJO(digimonCard);
				player.viewAttributesDigimonCard(createCardPOJO, opponent);
			}
		}
	}

	public void viewAttributesOptionCard(boolean opponent) {
		if(opponent) {
			Card supportCard = table.getRemotePlayer().getSupportCard();
			if(supportCard == null) {
				player.informError("O adversário não possuí nenhuma carta de suporte no campo de batalha");
			} else {
				CardPOJO createCardPOJO = createCardPOJO(supportCard);
				player.viewAttributesOptionCard(createCardPOJO, opponent);
			}
		} else {
			Card supportCard = table.getRemotePlayer().getSupportCard();
			if(supportCard == null) {
				player.informError("O adversário não possuí nenhuma carta de suporte no campo de batalha");
			} else {
				CardPOJO createCardPOJO = createCardPOJO(supportCard);
				player.viewAttributesDigimonCard(createCardPOJO, opponent);
			}
		}
	}
}