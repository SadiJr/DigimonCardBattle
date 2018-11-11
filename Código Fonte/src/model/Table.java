package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import com.google.gson.stream.MalformedJsonException;

import br.ufsc.inf.leobr.cliente.Jogada;
import enums.Effect;
import enums.Level;
import enums.Phase;

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

	public Table() {
		deck = new Deck();
		phase = Phase.WAIT;
		cemiteryLocalPlayer = new Cemitery();
		cemiteryRemotePlayer = new Cemitery();
	}
	
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

	public void createDeck() throws FileNotFoundException, MalformedJsonException {
		FileReader digimonFire = new FileReader(System.getProperty("user.dir") + "/cards/digimonFire.json");
		FileReader digimonGrass = new FileReader(System.getProperty("user.dir") + "/cards/digimonGrass.json");
		FileReader optionCard = new FileReader(System.getProperty("user.dir") + "/cards/optionCard.json");
		
		Gson gson = new GsonBuilder().create();
		BufferedReader fire = new BufferedReader(digimonFire);
		BufferedReader grass = new BufferedReader(digimonGrass);
		BufferedReader option = new BufferedReader(optionCard);
		
		JsonStreamParser f = new JsonStreamParser(fire);
		JsonStreamParser g = new JsonStreamParser(grass);
		JsonStreamParser o = new JsonStreamParser(option);

		while (f.hasNext() && g.hasNext()) {
			JsonElement fi = f.next();
			JsonElement gr = g.next();
			if (fi.isJsonObject() && gr.isJsonObject()) {
				DigimonCard digimonFireCard = gson.fromJson(fi, DigimonCard.class);
				DigimonCard digimonGrassCard = gson.fromJson(gr, DigimonCard.class);
				deck.getCards().add(digimonFireCard);
				deck.getCards().add(digimonGrassCard);
			} else {
				throw new MalformedJsonException("Possível erro no json");
			}
		}
		
		while (o.hasNext()) {
			JsonElement opt = o.next();
			if(opt.isJsonObject()) {
				OptionCard op = gson.fromJson(opt, OptionCard.class);
				deck.getCards().add(op);
			} else {
				throw new MalformedJsonException("Possível erro no json");
			}
		}
//		for(Card c : deck.getCards()) {
//			if(c instanceof DigimonCard) {
//				DigimonCard d = (DigimonCard) c;
//				System.out.println("Name: " + c.getName() + "\nEffect: "
//						+ (c.getCardEffect() == null ? "Não há" : c.getCardEffect().name()) + "\nDescription: "
//						+ (c.getCardEffect() == null ? "Não há" : c.getCardEffect().getDescription()) + "\nPath: "
//						+ c.getPathToImage() + "\nHP: " + d.getHp() + "\n" + "ATK1: " + d.getAttack1() + "\nATK2: "
//						+ d.getAttack2() + "\nATK3: " + d.getAttack3() + "\nDP: " + d.getDp() + "\nP:" + d.getP()
//						+ "\nSpecialty: " + d.getSpecialty().name() + "\nLevel: " + d.getLevel().name() + "\n\n\n");
//			} else {
//				System.out.println("Name: " + c.getName() +"\nEffect: " + c.getCardEffect().name() + "\nDescription: " +
//						c.getDescriptionEffect() + "\nPath: " + c.getPathToImage() + "\n\n\n");
//			}
//		}
	}

	public void createLocalPlayer(String name, int id) {
		this.localPlayer = new Player(name, id);
	}

	public void distributeCards() {
		System.err.println("\n\nCartas no deck do usuário");
		ArrayList<Card> cards = (ArrayList<Card>) deck.getCards();
		ArrayList<Player> listPlayers = (ArrayList<Player>) getListPlayers();
		int result = (int) (Math.random() * 2);
		Player player1 = listPlayers.get(result);
		Player player2 = listPlayers.get(result == 0 ? 1 : 0);
		for(int i=0; i < 28; i+=2) {
			Card card = cards.get(i);
			Card card2 = cards.get(i+1);
			player1.getDeck().getCards().add(card);
			player2.getDeck().getCards().add(card2);
		}
		
		for(int i = 0; i < 6; i++) {
			int optionResultP1 = 28 + (int) (Math.random() * 12);
			int optionResultP2 = 28 + (int) (Math.random() * 12);
			Card card = cards.get(optionResultP1);
			Card card2 = cards.get(optionResultP2);
			player1.getDeck().getCards().add(card);
			player2.getDeck().getCards().add(card2);
		}
		
//		for(Player p : getListPlayers()) {
//			System.err.println("\n\nCartas no deck do usuário: " + p.getName() + "\n\n");
//			for(Card c : p.getDeck().getCards()) {
//				if(c instanceof DigimonCard) {
//					DigimonCard d = (DigimonCard) c;
//					System.out.println("Name: " + c.getName() + "\nEffect: "
//							+ (c.getCardEffect() == null ? "Não há" : c.getCardEffect().name()) + "\nDescription: "
//							+ (c.getCardEffect() == null ? "Não há" : c.getCardEffect().getDescription()) + "\nPath: "
//							+ c.getPathToImage() + "\nHP: " + d.getHp() + "\n" + "ATK1: " + d.getAttack1() + "\nATK2: "
//							+ d.getAttack2() + "\nATK3: " + d.getAttack3() + "\nDP: " + d.getDp() + "\nP:" + d.getP()
//							+ "\nSpecialty: " + d.getSpecialty().name() + "\nLevel: " + d.getLevel().name() + "\n\n\n");
//				} else {
//					System.out.println("Name: " + c.getName() +"\nEffect: " + c.getCardEffect().name() + "\nDescription: " +
//							c.getDescriptionEffect() + "\nPath: " + c.getPathToImage() + "\n\n\n");
//				}
//			}
//		}
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

	public Player verifyWinner() {
		if(localPlayer.getVictories() == 3)
			return localPlayer;
		if(remotePlayer.getVictories() == 3)
			return remotePlayer;
		return null;
	}

	public void createRemotePlayer(String name, int id) {
		this.remotePlayer = new Player(name, id);
	}

	public void discardHand() {
		Collection<Card> hand = localPlayer.getHand();
		for(Card card : hand) {
			cemiteryLocalPlayer.addCard(card);
		}
		localPlayer.setHand(null);
		addNewHand();
	}

	public void downDigimonCard(String nameCard) throws Exception {
		if(existsDigimonCardOnSlot()) {
			throw new Exception("Já existe uma DigimonCard no campo de batalha! Pule para a próxima fase, caso não queira descartar sua mão atual");
		}
		for(Card card : localPlayer.getHand()) {
			if(card.getName().equals(nameCard)) {
				if(isDigimonCard(card)) {
					DigimonCard digimon = (DigimonCard) card;
					Level level = digimon.getLevel();
					switch(level) {
					case C:
						localPlayer.setDigimonCardIrregularLevelC(digimon);
						localPlayer.getHand().remove(digimon);
						break;
					case U:
						localPlayer.setDigimonCardIrregularLevelU(digimon);
						localPlayer.getHand().remove(digimon);
						break;
					default:
						localPlayer.setDigimonCard(digimon);
						localPlayer.getHand().remove(digimon);						
						break;
					}
					return;
				} else {
					throw new Exception("A carta selecionada não é uma DigimonCard!");
				}
			}
		}
		throw new Exception("A carta selecionada não existe na mão do jogador. Possível erro!");
	}

	public void sacrificeCard(String cardName) throws Exception {
		for(Card card : localPlayer.getHand()) {
			if(card.getName().equals(cardName)) {
				if(isDigimonCard(card)) {
					DigimonCard digimon = (DigimonCard) card;
					int p = digimon.getP();
					localPlayer.setDp(localPlayer.getDp() + p);
					localPlayer.getHand().remove(digimon);
					cemiteryLocalPlayer.addCard(digimon);
					return;
				} else {
					throw new Exception("A carta selecionada não é uma DigimonCard!");
				}
			}
		}
		throw new Exception("A carta selecionada não existe na mão do jogador. Possível erro!");
	}

	public boolean isDigimonCard(Card card) {
		if(card instanceof DigimonCard)
			return true;
		return false;
	}

	public Cemitery getCemiteryLocalPlayer() {
		return this.cemiteryLocalPlayer;
	}
	
	public void setCemiteryLocalPlayer(Cemitery cemiteryLocalPlayer) {
		this.cemiteryLocalPlayer = cemiteryLocalPlayer;
	}

	public Cemitery getCemiteryRemotePlayer() {
		return this.cemiteryRemotePlayer;
	}

	public void setCemiteryRemotePlayer(Cemitery cemiteryRemotePlayer) {
		this.cemiteryRemotePlayer = cemiteryRemotePlayer;
	}

	public Player getFirstPlayer() {
		return this.firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public void addNewHand() {
		createHandLocalPlayer();
	}

	public Card getCardByName(String name) {
		for(Card card : localPlayer.getHand()) {
			if(card.getName().equals(name)) {
				return card;
			}
		}
		for(Card card : remotePlayer.getHand()) {
			if(card.getName().equals(name)) {
				return card;
			}
		}
		return null;
	}

	public boolean existsDigimonCardOnSlot() {
		if(localPlayer.getDigimonCard() != null)
			return true;
		return false;
	}

	public void updateCard(String name) throws Exception {
		if(localPlayer.getDigimonCard() != null) {
			Card card = getCardByName(name);
			if(card != null) {
				for(Card c : localPlayer.getHand()) {
					if(c.equals(card)) {
						if(isDigimonCard(card)) {
							if(levelNecessary(((DigimonCard) card).getLevel())) {
								if(isDpEnough(((DigimonCard) card).getDp())) {
									cemiteryLocalPlayer.addCard(localPlayer.getDigimonCard());
									localPlayer.setDigimonCard((DigimonCard) card);
									localPlayer.setDp(0);
								} else {
									throw new Exception("Você não possuí DP suficiente para realizar essa evolução");
								}
							} else {
								throw new Exception("Essa carta não pode ser evoluída para esse nível!");
							}
						} else {
							throw new Exception("Tantativa inválida de evoluir uma carta que não é DigimonCard!");
						}
					}
				}
			} else {
				throw new Exception("A carta selecionada para evolução não existe em sua mão!");
			}
		} else {
			throw new Exception("Erro! Não existe nenhuma digimonCard no campo de batalha");
		}
	}
	
	public boolean levelNecessary(Level level) throws Exception {
		Level levelPlayer = localPlayer.getDigimonCard().getLevel();
		switch (level) {
		case C:
			if(levelPlayer.equals(Level.R))
				return true;
			break;

		case U:
			if(levelPlayer.equals(Level.C))
				return false;
		default:
			throw new Exception("Essa carta não pode ser evoluída para esse nível!");
		}
		return false;
	}

	public boolean isDpEnough(int dpNecessary) {
		if(localPlayer.getDp() >= dpNecessary)
			return true;
		return false;
	}

	public void removeCardOfHand(Card card) throws Exception {
		for(Card hand : localPlayer.getHand()) {
			if(card.equals(hand)) {
				localPlayer.getHand().remove(card);
			}
		}
		throw new Exception("A carta selecionada não existe na mão do jogador!");
	}

	public void downSupportCard(String supportName) throws Exception {
		for(Card card : localPlayer.getHand()) {
			if(card.getName().equals(supportName)) {
				localPlayer.setSupportCard(card);
				localPlayer.getHand().remove(card);
			}
		}
		throw new Exception("A carta selecionada não existe na mão do jogador. Possível erro!");
	}

	public void createHandLocalPlayer() {
		int size = localPlayer.getHand().size();
		if(size < 4) {
			while(size < 4 && !localPlayer.getDeck().getCards().isEmpty()) {
				addMissingCards();
			}
		}
	}
	
	public Collection<Player> getListPlayers() {
		Collection<Player> players = new ArrayList<>();
		players.add(localPlayer);
		players.add(remotePlayer);
		return players;
	}

	public int getSizeHandLocalPlayer() {
		return localPlayer.getHand().size();
	}

	public void addMissingCards() {
		ArrayList<Card> cards = (ArrayList<Card>) localPlayer.getDeck().getCards();
		Card card = cards.get(0);
		localPlayer.getDeck().getCards().remove(card);
		localPlayer.getHand().add(card);
	}

	public boolean existsDigimonCardInHand() {
		for (Card card : localPlayer.getHand()) {
			if(card instanceof DigimonCard)
				return true;
		} 
		return false;
	}
	
	public boolean existsDigimonCardInDeck() {
		for(Card card : localPlayer.getDeck().getCards()) {
			if(card instanceof DigimonCard)
				return true;
		}
		return false;
	}

	public void supportCardEffect(Player player) throws Exception {
		DigimonCard digimonCard = player.getDigimonCard();
		Card supportCard = player.getSupportCard();
		Effect cardEffect = supportCard.getCardEffect();
		switch (cardEffect) {
		
		case ATK1_100:
			digimonCard.setAttack1(digimonCard.getAttack1() + 100);
			player.setDigimonCard(digimonCard);
			break;
			
		case ATK1_X2:
			digimonCard.setAttack1(digimonCard.getAttack1() * 2);
			player.setDigimonCard(digimonCard);
			break;
			
		case ATK300:
			digimonCard.setAttack1(digimonCard.getAttack1() + 300);
			digimonCard.setAttack2(digimonCard.getAttack2() + 300);
			digimonCard.setAttack3(digimonCard.getAttack3() + 300);
			player.setDigimonCard(digimonCard);
			break;
		
		case ATK3_X2:
			digimonCard.setAttack3(digimonCard.getAttack3() * 2);
			player.setDigimonCard(digimonCard);
			break;
			
		case ATK500:
			digimonCard.setAttack1(digimonCard.getAttack1() + 500);
			digimonCard.setAttack2(digimonCard.getAttack2() + 500);
			digimonCard.setAttack3(digimonCard.getAttack3() + 500);
			player.setDigimonCard(digimonCard);
			break;
		
		case C_ATK400:
			if(digimonCard.getLevel().equals(Level.C)) {
				digimonCard.setAttack1(digimonCard.getAttack1() + 400);
				digimonCard.setAttack2(digimonCard.getAttack2() + 400);
				digimonCard.setAttack3(digimonCard.getAttack3() + 400);
				player.setDigimonCard(digimonCard);
			}
			break;
		
		case HP1000:
			digimonCard.setHp(digimonCard.getHp() + 1000);
			player.setDigimonCard(digimonCard);
			break;
		
		case HP1_500_HP2_200:
			player.getDigimonCard().setHp(player.getDigimonCard().getHp() + 500);
			if(player.equals(localPlayer)) {
				remotePlayer.getDigimonCard().setHp(remotePlayer.getDigimonCard().getHp() + 200);
			} else {
				localPlayer.getDigimonCard().setHp(remotePlayer.getDigimonCard().getHp() + 200);
			}
			break;
		
		case HP300:
			digimonCard.setHp(digimonCard.getHp() + 300);
			player.setDigimonCard(digimonCard);
			break;
		
		case HP500:
			digimonCard.setHp(digimonCard.getHp() + 500);
			player.setDigimonCard(digimonCard);
			break;

		case U_ATK400:
			if(digimonCard.getLevel().equals(Level.U)) {
				digimonCard.setAttack1(digimonCard.getAttack1() + 400);
				digimonCard.setAttack2(digimonCard.getAttack2() + 400);
				digimonCard.setAttack3(digimonCard.getAttack3() + 400);
				player.setDigimonCard(digimonCard);
			}
			break;
		
		default:
			throw new Exception("Erro de programação na fase de batalha!");
		}
		
		player.setSupportCard(null);
		addCardToCemiteryByPlayer(player, supportCard);
	}

	public void battleTurn() throws Exception {
		Player first = getFirstPlayer();
		int attackChoice = first.getAttackChoice();
		int damage = 0;
		switch (attackChoice) {
		
		case 1:
			damage = first.getDigimonCard().getAttack1();
			break;
		
		case 2:
			damage = first.getDigimonCard().getAttack2();
			break;

		case 3:
			damage = first.getDigimonCard().getAttack3();
			break;
			
		default:
			throw new Exception("Um erro inesperado ocorreu!");
		}
		
		boolean attack = attack(localPlayer.equals(first) ? remotePlayer : localPlayer, damage);

		if(attack) {
			return;
		} else {
			Player second = localPlayer.equals(first) ? remotePlayer : localPlayer;
			int secondAttackChoice = second.getAttackChoice();
			int secondDamage = 0;
			switch (secondAttackChoice) {
			
			case 1:
				secondDamage = second.getDigimonCard().getAttack1();
				break;
			
			case 2:
				secondDamage = second.getDigimonCard().getAttack2();
				break;

			case 3:
				secondDamage = second.getDigimonCard().getAttack3();
				break;
				
			default:
				throw new Exception("Um erro inesperado ocorreu!");
			}
			
			attack(first, secondDamage);
		}
	}
	
	public boolean attack(Player playerAttacked, int attack) {
		playerAttacked.getDigimonCard().setHp(playerAttacked.getDigimonCard().getHp() - attack);
		if(playerAttacked.getDigimonCard().getHp() <= 0)
			return true;
		return false;
	}

	public void addCardToCemiteryByPlayer(Player player, Card card) {
		if(player.equals(localPlayer)) {
			cemiteryLocalPlayer.addCard(card);
		} else {
			cemiteryRemotePlayer.addCard(card);
		}
	}
}