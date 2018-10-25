package game;

import javax.swing.JOptionPane;

import enums.ActionType;
import enums.CharacterType;
import enums.TileType;

public class Game {
	protected Player player1;
	protected Player player2;
	protected boolean matchGoing;
	protected boolean beginTurn;
	protected Board board;

	public Game() {
		this.matchGoing = false;
		this.board = new Board(32, 32);
	}

	public Action buyCharacter(CharacterType type, Player player) {
		Character character;
		Position position;
		if (checkPlayerCanBuy(player, type)) {
			character = new Character(type);
			position = placeCharacter(character, player);
			if (position != null) {
				player.addCharacter(character);
				System.out.println("wallet: " + player.getWallet());
				player.deduct_money(character.getType().getValue());
				System.out.println("Character: " + character.getType() + " price: " + character.getType().getValue());
				System.out.println("wallet: " + player.getWallet());
				System.out.println("X: " + position.getAxisX() + " Y: " + position.getAxisY());
				return new Action(0, 0, position.getAxisX(), position.getAxisY(), ActionType.BUY, character.getType());
			}

		}
		return null;
	}

	private boolean checkPlayerCanBuy(Player player, CharacterType type) {
		if (type.getValue() <= player.getWallet()) {
			return true;
		}
		System.out.println("you ran out of money: ");
		return false;
	}

	public Position placeCharacter(Character character, Player player) {
		Position mainBase = player.getMainBase();
		Position positionToSpawn = this.board.getPositionToSpawn(mainBase);
		if (positionToSpawn != null) {
			positionToSpawn.setCharacter(character);
			positionToSpawn.setOccupied(true);
			return positionToSpawn;
		}

		return null;

	}

	public void finishBuy(Player player) {
		player.setBeginTurn(false);
	}

	public void makeAttack(Action action, Player player) {
		Player opponent = this.getOpponent(player);
		Position selectedPosition = this.board.getPosition(action.getInitX(), action.getInitY());
		Position clickPosition = this.board.getPosition(action.getEndX(), action.getEndY());
		Character character = selectedPosition.getCharacter();
		System.out.println(inRange(selectedPosition, clickPosition, action.getActionType()));
		if (inRange(selectedPosition, clickPosition, action.getActionType())) {
			opponent.removeCharacter(character);
			clickPosition.setCharacter(null);
			character.setAttacked(true);
		}

	}

	public Action makeAction(int x, int y, Player player) {
		Action action;
		Position selectedPosition = player.getSelectedPosition();
		Position clickPosition = this.board.getPosition(x, y);
		Character character = clickPosition.getCharacter();
		System.out.println("clicked position - X: " + clickPosition.getAxisX() + " Y: " + clickPosition.getAxisY());
		if (character != null) { // SELECT
			if (player.getCharacterOwnership(character)) {
				player.setSelectedPosition(clickPosition);
				System.out.println(
						"selected position - X: " + clickPosition.getAxisX() + " Y: " + clickPosition.getAxisY());
				return null;

			} else { // ATTACK
				System.out.println(player.getSelectedPosition() == null);
				if (player.getSelectedPosition() != null) {
					action = new Action(selectedPosition.getAxisX(), selectedPosition.getAxisY(),
							clickPosition.getAxisX(), clickPosition.getAxisY(), ActionType.ATTACK, null);
					this.makeAttack(action, player);
					return action;

				}
			}
		} else { // MOVE
			if (selectedPosition != null) {
				action = new Action(selectedPosition.getAxisX(), selectedPosition.getAxisY(), clickPosition.getAxisX(),
						clickPosition.getAxisY(), ActionType.MOVE, null);
				this.makeMovement(action, player);
				this.captureBase(clickPosition, player);
				return action;

			}
			return null;
		}
		return null;
	}

	public void makeMovement(Action action, Player player) {
		Position selectedPosition = this.board.getPosition(action.getInitX(), action.getInitY());
		Position clickPosition = this.board.getPosition(action.getEndX(), action.getEndY());
		Player opponent = this.getOpponent(player);
		Character character = selectedPosition.getCharacter();
		if (inRange(selectedPosition, clickPosition, action.getActionType()) && !character.isMoved()
				&& this.board.checkActionThroughTile(clickPosition)) {
			clickPosition.setCharacter(character);
			selectedPosition.setCharacter(null);
			character.setMoved(true);
			captureBase(clickPosition, player);
		}
	}

	private void captureBase(Position clickPosition, Player player) {
		Player opponent = this.getOpponent(player);
		Position mainBase = opponent.getMainBase();
		System.out.println("capt base");
		if (player.getListBase().contains(clickPosition)) {
			if (clickPosition.getTile() == TileType.SECONDARY_BASE) {
				if (opponent.getListBase().contains(clickPosition)) {
					opponent.removeBase(clickPosition);
				}

			}
		}
		player.addBase(clickPosition);
		System.out.println("you captured one base");
		if (clickPosition.getTile() == mainBase.getTile()) {
			player.setWinner(true);
		}
	}

	public Action changeTurn(Player player) {
		if (player.isTurn()) {
			player.resetCharacters();
			player.addMoney();
			return new Action(0, 0, 0, 0, ActionType.CHANGETURN, null);
		}
		return null;
	}

	public void beginGame(Player player, int position) {
		if (position == 1) {
			player1 = player;
			this.player1.setBeginTurn(true);
			this.player1.setTurn(true);
			this.player1.setMainBase(this.board.getMainBase1());

			player2 = new Player("Player 2");
			this.player2.setMainBase(this.board.getMainBase2());

		}
		if (position == 2) {
			player2 = player;
			this.player2.setMainBase(this.board.getMainBase2());

			player1 = new Player("Player 1");
			this.player1.setMainBase(this.board.getMainBase1());

		}
	}

	private boolean inRange(Position selectedPosition, Position clickPosition, ActionType type) {
		Character character = selectedPosition.getCharacter();

		int rangeCharacter;
		int distance = this.board.getDistance(selectedPosition, clickPosition);

		if (type == ActionType.ATTACK) {
			rangeCharacter = character.getType().getRangeAttack();
			System.out.println("distance " + distance + "range attack" + rangeCharacter);
			if ((rangeCharacter - distance) >= 0) {

				return true;
			}

		} else {
			rangeCharacter = character.getType().getRangeMove();
			System.out.println("distance: " + distance + " - range move: " + rangeCharacter);
			if ((rangeCharacter - distance) >= 0) {
				return true;
			}
		}
		return false;
	}

	public Board getBoard() {
		return this.board;
	}

	public boolean getMatchGoing() {
		return this.matchGoing;
	}

	public void setMatchGoing(boolean b) {
		this.matchGoing = b;
	}

	public Player getPlayer(Player player) {
		if (this.player1 == player) {
			return this.player1;
		}
		return this.player2;
	}

	public Player getOpponent(Player player) {
		if (this.player1 == player) {
			return this.player2;
		}
		return this.player1;
	}
}
