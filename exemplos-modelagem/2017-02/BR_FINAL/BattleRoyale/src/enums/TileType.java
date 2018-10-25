package enums;

public enum TileType {
	MAIN_BASE1(500),
	MAIN_BASE2(500),
	SECONDARY_BASE(200),
	WATER(0),
	GRASS(0),
	BRIDGE(0),
	OBSTACLE(0);

	protected int moneyGiven;

	TileType(int moneyGiven) {
		this.moneyGiven = moneyGiven;
	}

	public int getMoneyGiven() {
		return this.moneyGiven;
	}
}
