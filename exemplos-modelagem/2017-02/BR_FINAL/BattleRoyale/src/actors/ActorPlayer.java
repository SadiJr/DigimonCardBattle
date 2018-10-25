package actors;

import game.Control;
import game.Player;
import gui.GUIBoard;
import gui.GUIBuyCharacter;
import gui.GUIMenu;

public class ActorPlayer {
	protected Control control;
	protected GUIBoard g_board;
	protected GUIMenu menu;
	protected String name;
	protected ActorNetGames netGames;

	public ActorPlayer(Control control) {
		super();
		this.control = control;
		this.netGames = new ActorNetGames(control);
	}

	public void initBoard() {
		this.g_board = new GUIBoard(control);
	}

	public void updateBoard() {
		this.g_board.updateBoard();
	}

	public void showWin(Player player) {
		System.out.println(player.getName());
	}

	public void buyCharacter() {
		new GUIBuyCharacter(control);
	}

	public void beginMenu() {
		this.menu = new GUIMenu(control);
	}

	public void showLose(Player opponent) {
		if (opponent.isWinner()) {
			System.out.println(opponent.getName());
		}

	}
}
