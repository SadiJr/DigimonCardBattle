package game;

import java.util.ArrayList;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Player implements Jogada {

	protected String name;
	protected int wallet;
	protected boolean turn;
	protected boolean beginTurn;
	protected boolean winner;
	protected Position selectedPosition;
	protected ArrayList<Character> listCharacter;
	protected ArrayList<Position> listBase;

	public Player(String name) {
		this.name = name;
		this.wallet = 500;
		this.turn = false;
		this.beginTurn = false;
		this.winner = false;
		this.listBase = new ArrayList<>();
		this.listCharacter = new ArrayList<>();
	}

	public int getWallet() {
		return wallet;
	}

	public void addBase(Position p) {
		this.listBase.add(p);
	}

	public void removeBase(Position p) {
		if (this.listBase.size() > 1) {
			this.listBase.remove(p);
		}
	}

	public ArrayList<Position> getListBase() {
		return this.listBase;
	}

	public void deduct_money(int val) {
		this.wallet = this.wallet - val;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public boolean isBeginTurn() {

		return beginTurn;
	}

	public void setBeginTurn(boolean beginTurn) {
		this.beginTurn = beginTurn;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public Position getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(Position selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	public ArrayList<Character> getListCharacter() {
		return listCharacter;
	}

	public void removeCharacter(Character character) {
		this.listCharacter.remove(character);
	}

	public void addCharacter(Character character) {
		this.listCharacter.add(character);
	}

	public boolean isPlayerCharacter(Character character) {
		for (int i = 0; i < this.listCharacter.size(); i++) {
			if (character == this.listCharacter.get(i)) {
				return true;
			}
		}
		return false;
	}

	public boolean getCharacterOwnership(Character character) {
		for (int i = 0; i < this.listCharacter.size(); i++) {
			if (this.listCharacter.get(i) == character) {
				return true;
			}
		}
		return false;
	}

	public void resetCharacters() {
		Character character;
		for (int i = 0; i < this.listCharacter.size(); i++) {
			character = this.listCharacter.get(i);
			if (character.isAttacked() || character.isMoved()) {
				character.setAttacked(false);
				character.setMoved(false);
			}
		}
	}

	public boolean checkTurn() {
		Character character;
		if (this.isTurn()) {
			for (int i = 0; i < this.listCharacter.size(); i++) {
				character = this.listCharacter.get(i);
				if (!character.isMoved() || !character.isAttacked()) {
					return true;
				}
			}
			System.out.println("No move left but change turn.");
			return false;
		}
		System.out.println(this.name);
		System.out.println("Not your turn.");
		return false;
	}

	public Position getMainBase() {
		if (!this.listBase.isEmpty()) {
			return this.listBase.get(0);
		}
		return null;
	}

	public void setMainBase(Position mainBase) {
		if (mainBase != null) {
			this.listBase.add(mainBase);
		}
	}

	public void addMoney() {
		int money = 0;
		if (this.listBase != null) {
			for (int i = 0; i < this.listBase.size(); i++) {
				money = money + this.listBase.get(i).getTile().getMoneyGiven();
			}
		}
		this.wallet = this.wallet + money;
	}

	public void setListBase(ArrayList<Position> list) {
		this.listBase = list;
	}

	public void setListCharacter(ArrayList<Character> list) {
		this.listCharacter = list;
	}

}
