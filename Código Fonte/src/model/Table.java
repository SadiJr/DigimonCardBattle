package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonStreamParser;
import com.google.gson.stream.MalformedJsonException;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import br.ufsc.inf.leobr.cliente.Jogada;
import enums.Effect;
import enums.Level;
import enums.Phase;

public class Table implements Jogada {
	private static final long serialVersionUID = 1L;
	private Deck deck;
	private Player localPlayer;
	private Player remotePlayer;
	private boolean gameInProggress;
	private boolean connected;
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
		FileReader digimonFire = new FileReader("cards/digimonFire.json");
		FileReader digimonGrass = new FileReader("cards/digimonGrass.json");
		FileReader optionCard = new FileReader("cards/optionCard.json");

		Gson gson = new GsonBuilder().create();
		BufferedReader fire = new BufferedReader(digimonFire);
		BufferedReader grass = new BufferedReader(digimonGrass);
		BufferedReader option = new BufferedReader(optionCard);

		JsonStreamParser f = new JsonStreamParser(fire);
		JsonStreamParser g = new JsonStreamParser(grass);
		JsonStreamParser o = new JsonStreamParser(option);

		while (f.hasNext()) {
			JsonElement fi = f.next();
			if (fi.isJsonObject()) {
				DigimonCard digimonFireCard = gson.fromJson(fi, DigimonCard.class);
				deck.getCards().add(digimonFireCard);
			} else {
				throw new MalformedJsonException("Possível erro no json");
			}
		}

		while (g.hasNext()) {
			JsonElement gr = g.next();
			if (gr.isJsonObject()) {
				DigimonCard digimonGrassCard = gson.fromJson(gr, DigimonCard.class);
				deck.getCards().add(digimonGrassCard);
			} else {
				throw new MalformedJsonException("Possível erro no json");
			}
		}

		while (o.hasNext()) {
			JsonElement opt = o.next();
			if (opt.isJsonObject()) {
				OptionCard op = gson.fromJson(opt, OptionCard.class);
				deck.getCards().add(op);
			} else {
				throw new MalformedJsonException("Possível erro no json");
			}
		}
		for (Card c : deck.getCards()) {
			if (c instanceof DigimonCard) {
				DigimonCard d = (DigimonCard) c;
				System.out.println("Name: " + c.getName() + "\nEffect: "
						+ (c.getCardEffect() == null ? "Não há" : c.getCardEffect().name()) + "\nDescription: "
						+ (c.getCardEffect() == null ? "Não há" : c.getCardEffect().getDescription()) + "\nPath: "
						+ c.getPathToImage() + "\nHP: " + d.getHp() + "\n" + "ATK1: " + d.getAttack1() + "\nATK2: "
						+ d.getAttack2() + "\nATK3: " + d.getAttack3() + "\nDP: " + d.getDp() + "\nP:" + d.getP()
						+ "\nSpecialty: " + d.getSpecialty().name() + "\nLevel: " + d.getLevel().name() + "\n\n\n");
			} else {
				System.out.println("Name: " + c.getName() + "\nEffect: " + c.getCardEffect().name() + "\nDescription: "
						+ c.getDescriptionEffect() + "\nPath: " + c.getPathToImage() + "\n\n\n");
			}
		}
	}

	public void createLocalPlayer(String name, int id) {
		this.localPlayer = new Player(name, id);
	}

	public void distributeCards() {
		ArrayList<Card> cards = (ArrayList<Card>) deck.getCards();
		ArrayList<Player> listPlayers = (ArrayList<Player>) getListPlayers();
		Player player1 = listPlayers.get(0);
		Player player2 = listPlayers.get(1);
		for (int i = 0; i < 22; i++) {
			int z = (int) (Math.random() * 14);
			Card card = cards.get(z);
			player1.getDeck().getCards().add(card);
		}
		for (int i = 0; i < 22; i++) {
			int z = 14 + (int) (Math.random() * 14);
			Card card = cards.get(z);
			player2.getDeck().getCards().add(card);
		}

		for (int i = 0; i < 8; i++) {
			int optionResultP1 = 28 + (int) (Math.random() * 12);
			int optionResultP2 = 28 + (int) (Math.random() * 12);
			Card card = cards.get(optionResultP1);
			Card card2 = cards.get(optionResultP2);
			player1.getDeck().getCards().add(card);
			player2.getDeck().getCards().add(card2);
		}

		for (Player p : getListPlayers()) {
			System.err.println("\n\nCartas no deck do usuário: " + p.getName() + "\n\n");
			for (Card c : p.getDeck().getCards()) {
				if (c instanceof DigimonCard) {
					DigimonCard d = (DigimonCard) c;
					System.out.println("Name: " + c.getName() + "\nEffect: "
							+ (c.getCardEffect() == null ? "Não há" : c.getCardEffect().name()) + "\nDescription: "
							+ (c.getCardEffect() == null ? "Não há" : c.getCardEffect().getDescription()) + "\nPath: "
							+ c.getPathToImage() + "\nHP: " + d.getHp() + "\n" + "ATK1: " + d.getAttack1() + "\nATK2: "
							+ d.getAttack2() + "\nATK3: " + d.getAttack3() + "\nDP: " + d.getDp() + "\nP:" + d.getP()
							+ "\nSpecialty: " + d.getSpecialty().name() + "\nLevel: " + d.getLevel().name() + "\n\n\n");
				} else {
					System.out.println(
							"Name: " + c.getName() + "\nEffect: " + c.getCardEffect().name() + "\nDescription: "
									+ c.getDescriptionEffect() + "\nPath: " + c.getPathToImage() + "\n\n\n");
				}
			}
		}
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
		if (localPlayer.getVictories() == 3)
			return localPlayer;
		if (remotePlayer.getVictories() == 3)
			return remotePlayer;
		return null;
	}

	public void createRemotePlayer(String name, int id) {
		this.remotePlayer = new Player(name, id);
	}

	public void discardHand() {
		Collection<Card> hand = localPlayer.getHand();
		for (Card card : hand) {
			cemiteryLocalPlayer.addCard(card);
		}
		localPlayer.setHand(null);
		addNewHand();
	}

	public void downDigimonCard(String nameCard) throws Exception {
		if (existsDigimonCardOnSlot()) {
			throw new Exception(
					"Já existe uma DigimonCard no campo de batalha! Pule para a próxima fase, caso não queira descartar sua mão atual");
		}
		for (Card card : localPlayer.getHand()) {
			if (card.getName().equals(nameCard)) {
				if (isDigimonCard(card)) {
					DigimonCard digimon = (DigimonCard) card;
					Level level = digimon.getLevel();
					switch (level) {
					case C:
						localPlayer.setDigimonCardIrregularLevelC(digimon);
						break;
					case U:
						localPlayer.setDigimonCardIrregularLevelU(digimon);
						break;
					default:
						localPlayer.setDigimonCard(digimon);
						break;
					}
					ArrayList<Card> c = (ArrayList<Card>) localPlayer.getHand();
					int indexOf = c.indexOf(digimon);
					System.err.println(indexOf);
					c.set(indexOf, null);
					return;
				} else {
					throw new Exception("A carta selecionada não é uma DigimonCard!");
				}
			}
		}
		throw new Exception("A carta selecionada não existe na mão do jogador. Possível erro!");
	}

	public void sacrificeCard(String cardName) throws Exception {
		for (Card card : localPlayer.getHand()) {
			if (card != null && card.getName().equals(cardName)) {
				if (isDigimonCard(card)) {
					DigimonCard digimon = (DigimonCard) card;
					int p = digimon.getP();
					localPlayer.setDp(localPlayer.getDp() + p);
					ArrayList<Card> hand = (ArrayList<Card>) localPlayer.getHand();
					int indexOf = hand.indexOf(digimon);
					hand.set(indexOf, null);
					localPlayer.setHand(hand);
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
		if (card instanceof DigimonCard)
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

	public void addNewHand() {
		for (Card c : localPlayer.getHand()) {
			if(c != null)
				cemiteryLocalPlayer.addCard(c);
		}
		localPlayer.setHand(new ArrayList<>());
		createHandLocalPlayer();
	}

	public Card getCardByName(String name, boolean opponent) {
		if (opponent) {
			if (remotePlayer == null)
				return null;
			for (Card card : remotePlayer.getHand()) {
				if (card != null && card.getName().equals(name)) {
					return card;
				}
			}
		} else {
			if (localPlayer == null)
				return null;
			for (Card card : localPlayer.getHand()) {
				if (card != null && card.getName().equals(name)) {
					return card;
				}
			}
		}
		return null;
	}

	public boolean existsDigimonCardOnSlot() {
		if (localPlayer.getDigimonCard() != null)
			return true;
		return false;
	}

	public void updateCard(String name) throws Exception {
		if (localPlayer.getDigimonCard() != null) {
			Card card = getCardByName(name, false);
			if (card != null) {
				if (isDigimonCard(card)) {
					if (levelNecessary(((DigimonCard) card).getLevel())) {
						if (isDpEnough(((DigimonCard) card).getDp())) {
							cemiteryLocalPlayer.addCard(localPlayer.getDigimonCard());
							localPlayer.setDigimonCard((DigimonCard) card);
							localPlayer.setDp(0);
							ArrayList<Card> hand = (ArrayList<Card>) localPlayer.getHand();
							int indexOf = hand.indexOf(card);
							hand.set(indexOf, null);
							localPlayer.setHand(hand);
						} else {
							throw new Exception("Você não possuí DP suficiente para realizar essa evolução");
						}
					} else {
						throw new Exception("Essa carta não pode ser evoluída para esse nível!");
					}
				} else {
					throw new Exception("Tantativa inválida de evoluir uma carta que não é DigimonCard!");
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
			if (levelPlayer.equals(Level.R))
				return true;
			break;

		case U:
			if (levelPlayer.equals(Level.C))
				return false;
		default:
			throw new Exception("Essa carta não pode ser evoluída para esse nível!");
		}
		return false;
	}

	public boolean isDpEnough(int dpNecessary) {
		if (localPlayer.getDp() >= dpNecessary)
			return true;
		return false;
	}

	public void removeCardOfHand(Card card) throws Exception {
		for (Card hand : localPlayer.getHand()) {
			if (card.equals(hand)) {
				localPlayer.getHand().remove(card);
			}
		}
		throw new Exception("A carta selecionada não existe na mão do jogador!");
	}

	public void downSupportCard(String supportName) throws Exception {
		System.out.println("Tentando baixar a carta " + supportName + " como suporte");
		for (Card card : localPlayer.getHand()) {
			if (card != null && card.getName().equals(supportName)) {
				localPlayer.setSupportCard(card);
				ArrayList<Card> hand = (ArrayList<Card>) localPlayer.getHand();
				int indexOf = hand.indexOf(card);
				hand.set(indexOf, null);
				localPlayer.setHand(hand);
				return;
			}
		}
		throw new Exception("A carta selecionada não existe na mão do jogador. Possível erro!");
	}

	public void createHandLocalPlayer() {
		int size = localPlayer.getHand().size();
		if (size < 4) {
			while (size < 4 && !localPlayer.getDeck().getCards().isEmpty()) {
				addMissingCards();
				size++;
			}
		}
	}

	public Collection<Player> getListPlayers() {
		Collection<Player> players = new ArrayList<>();
		if (localPlayer.getId() == 1) {
			players.add(localPlayer);
			players.add(remotePlayer);
		} else {
			players.add(remotePlayer);
			players.add(localPlayer);
		}
		return players;
	}

	public int getSizeHandLocalPlayer() {
		return localPlayer.getHand().size();
	}

	public void addMissingCards() {
		ArrayList<Card> cards = (ArrayList<Card>) localPlayer.getDeck().getCards();
		int size = cards.size();
		int index = (int) Math.random() * size;
		Card card = cards.get(index);
		localPlayer.getDeck().getCards().remove(card);
		localPlayer.getHand().add(card);
	}

	public boolean existsDigimonCardInHand() {
		for (Card card : localPlayer.getHand()) {
			if (card instanceof DigimonCard)
				return true;
		}
		return false;
	}

	public boolean existsDigimonCardInDeck() {
		if(localPlayer.getDeck() == null || localPlayer.getDeck().getCards() == null)
			return false;
		for (Card card : localPlayer.getDeck().getCards()) {
			if (card != null && card instanceof DigimonCard)
				return true;
		}
		return false;
	}

	public void supportCardEffect(Player player) throws Exception {
		DigimonCard digimonCard = player.getDigimonCard();
		Card supportCard = player.getSupportCard();
		if (supportCard != null) {
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
				if (digimonCard.getLevel().equals(Level.C)) {
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
				if (player.equals(localPlayer)) {
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
				if (digimonCard.getLevel().equals(Level.U)) {
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
	}

	public void battleTurn() throws Exception {
		Player first = localPlayer.getId() == 1 ? localPlayer : remotePlayer;
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

		if (attack) {
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
		if (playerAttacked.getDigimonCard().getHp() <= 0)
			return true;
		return false;
	}

	public void addCardToCemiteryByPlayer(Player player, Card card) {
		if (player.equals(localPlayer)) {
			cemiteryLocalPlayer.addCard(card);
		} else {
			cemiteryRemotePlayer.addCard(card);
		}
	}

	public void restartAll() {
		deck = new Deck();
		remotePlayer = null;
		gameInProggress = false;
		phase = Phase.WAIT;
		turns = 0;
		cemiteryLocalPlayer = new Cemitery();
		cemiteryRemotePlayer = new Cemitery();
		localPlayer.setDeck(new Deck());
		localPlayer.setDigimonCard(null);
		localPlayer.setSupportCard(null);
		localPlayer.setVictories(0);
		localPlayer.setDp(0);
	}
}