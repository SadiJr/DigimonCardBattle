package enums;

public enum CharacterType {

	SCOUT(8, 2, 200), 
	RIFLEMAN(4, 4, 250),
	SUBMACHINE(3, 5, 300),
	SNIPER(3, 8, 350);

	protected int rangeMove;
	protected int rangeAttack;
	protected int value;

	CharacterType(int rangeMove, int rangeAttack, int value) {
		this.rangeMove = rangeMove;
		this.rangeAttack = rangeAttack;
		this.value = value;
	}

	public int getRangeMove() {
		return rangeMove;
	}

	public int getRangeAttack() {
		return rangeAttack;
	}

	public int getValue() {
		return value;
	}

}
