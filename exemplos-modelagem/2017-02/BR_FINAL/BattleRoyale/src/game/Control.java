package game;

import javax.swing.JOptionPane;

import actors.ActorNetGames;
import actors.ActorPlayer;
import enums.ActionType;
import enums.CharacterType;
import gui.GUIBoard;
import gui.GUIBuyCharacter;

public class Control {

	protected ActorNetGames actorNetgames;
	protected ActorPlayer actorPlayer;
	protected Game game;
	protected Player player;
	public boolean b;

	public Control() {
		this.actorNetgames = new ActorNetGames(this);
		this.actorPlayer = new ActorPlayer(this);
		this.player = new Player("");
		b = false;
	}

	public void initGame(int position) {
		b = true;
		this.game = new Game();
		this.game.beginGame(this.player, position);
		this.game.setMatchGoing(true);
		this.actorPlayer.initBoard();
		if (this.player.isTurn()) {
			this.actorPlayer.buyCharacter();
		}
	}

	public void buyCharacter(CharacterType type) {
		if (this.player.isTurn()) {
			Action action = this.game.buyCharacter(type, this.player);
			if (action != null) {
				this.actorNetgames.sendAction(action);
			}
		}
	}

	public void clickPosition(int x, int y) {

		if (this.player.isTurn()) {
			Action action = this.game.makeAction(x, y, this.player);
			if (this.player.isWinner()) {
				this.game.setMatchGoing(false);
				this.actorPlayer.showWin(this.player);
			}
			if (action != null) {
				this.actorNetgames.sendAction(action);
			}
		}
	}

	public void makeAction(Action action) {
		Player opponent = this.game.getOpponent(this.player);
		if (action.getActionType() == ActionType.ATTACK) {
			this.game.makeAttack(action, opponent);
		}
		if (action.getActionType() == ActionType.MOVE) {
			this.game.makeMovement(action, opponent);
			System.out.println(opponent.isWinner());
			if (opponent.isWinner()) {
				this.game.setMatchGoing(false);
				this.actorPlayer.showLose(opponent);
			}
		}
		if (action.actionType == ActionType.BUY) {
			this.game.buyCharacter(action.getCharType(), opponent);
		}
		if (action.getActionType() == ActionType.CHANGETURN) {
			this.game.changeTurn(opponent);
			this.player.setBeginTurn(true);
			this.player.setTurn(true);
			this.actorPlayer.buyCharacter();
		}
		this.updateBoard();
	}

	public void changeTurn() {
		if (this.player.isTurn()) {
			this.game.changeTurn(this.player);
			this.player.setBeginTurn(false);
			this.player.setTurn(false);
			Action action = new Action(0, 0, 0, 0, ActionType.CHANGETURN, null);
			this.actorNetgames.sendAction(action);
		}

	}

	public Board getBoard() {
		return this.game.getBoard();
	}

	public void finishBuy() {
		this.game.finishBuy(this.player);
	}

	public void updateBoard() {
		this.actorPlayer.updateBoard();
	}

	public void connect() {
		this.actorNetgames.connect("localhost", this.player.getName());
	}

	public void disconnect() {
		this.actorNetgames.disconnect();
	}

	public void beginGame() {
		this.actorNetgames.iniciarPartida();
	}

	public Player getPlayer() {
		return this.player;
	}

	public void start() {
		this.actorPlayer.beginMenu();
	}

	public boolean isMatchGoing() {
		return this.game.getMatchGoing();
	}

}
