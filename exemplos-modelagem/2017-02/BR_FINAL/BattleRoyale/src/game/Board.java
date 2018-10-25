package game;

import java.util.ArrayList;

import enums.CharacterType;
import enums.TileType;

public class Board {

	protected int height;
	protected int width;
	protected Position[][] board;
	protected ArrayList<Character> listCharacter;
	protected ArrayList<Position> listBase;
	private Position mainBase1;
	private Position mainBase2;

	public Board(int height, int width) {
		this.height = height;
		this.width = width;
		this.board = new Position[this.height][this.width];
		this.listBase = new ArrayList<>();
		this.listCharacter = new ArrayList<>();
		setBoard();
		setMap(1);
	}

	private void setBoard() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.board[i][j] = new Position(i, j, TileType.GRASS);
			}
		}
	}

	public void setMap(int map) {
		switch (map) {
		case 1:
			this.addMainBase1(this.getPosition(0, 15));
			this.addMainBase2(this.getPosition(31, 15));
			for (int i = 0; i < this.height; i++) {
				for (int j = 0; j < this.width; j++) {
					if (i > 12 && i < 23) {
						this.addWater(getPosition(i, j));
					}
					if (i > 12 && j > 5 && i < 23 && j < 11) {
						this.addBridge(getPosition(i, j));
					}

					if (i > 12 && j > 20 && i < 23 && j < 26) {
						this.addBridge(getPosition(i, j));
					}
					if (i > 15 && j > 10 && i < 19 && j < 26) {
						this.addBridge(getPosition(i, j));
					}
				}
			}

			this.addSecondaryBase(this.getPosition(17, 23));
			this.addSecondaryBase(this.getPosition(17, 8));
			this.addSecondaryBase(this.getPosition(17, 15));
			this.addSecondaryBase(this.getPosition(5, 15));
			this.addSecondaryBase(this.getPosition(26, 15));

		}
	}

	public boolean checkIfBase(Position position) {
		if (this.listBase.contains(position)) {
			return true;
		}
		return false;
	}

	public Position getPositionToSpawn(Position mainBase) {
		Position position;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				position = this.getPosition(mainBase.getAxisX() + i, mainBase.getAxisY() + j);
				if (position != null && position != mainBase && !position.isOccupied()) {
					return position;
				}
			}
		}
		return null;

	}

	public Position getPosition(int x, int y) {

		if (x < 0 || x > this.height || y < 0 || y > this.width) {
			return null;
		}
		return this.board[x][y];

	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	private void addMainBase1(Position mainBase) {
		if (mainBase != null) {

			this.mainBase1 = mainBase;
			this.listBase.add(mainBase);
			mainBase.setTile(TileType.MAIN_BASE1);

		}
	}

	private void addMainBase2(Position mainBase) {
		if (mainBase != null) {
			this.mainBase2 = mainBase;
			this.listBase.add(mainBase);
			mainBase.setTile(TileType.MAIN_BASE2);
		}
	}

	private void addSecondaryBase(Position position) {
		if (position != null) {
			this.listBase.add(position);
			position.setTile(TileType.SECONDARY_BASE);
		}

	}

	private void addWater(Position position) {
		if (position != null) {
			position.setTile(TileType.WATER);
		}
	}

	private void addBridge(Position position) {
		if (position != null) {
			position.setTile(TileType.BRIDGE);
		}
	}

	public Position getMainBase1() {
		return this.mainBase1;
	}

	public Position getMainBase2() {
		return this.mainBase2;
	}

	public boolean checkActionThroughTile(Position clickPosition) {
		if (clickPosition.getTile() != TileType.WATER) {
			return true;
		}
		return false;
	}

	public int getDistance(Position selectedPosition, Position clickPosition) {
		return Math.abs(selectedPosition.getAxisX() - clickPosition.getAxisX())
				+ Math.abs(selectedPosition.getAxisY() - clickPosition.getAxisY());
	}
}