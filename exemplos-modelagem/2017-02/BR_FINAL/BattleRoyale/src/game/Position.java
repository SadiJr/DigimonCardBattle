package game;

import enums.TileType;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Position implements Jogada {

	protected int axisX;
	protected int axisY;
	protected Player player;
	protected boolean occupied;
	protected Character character;
	protected TileType tile;
	protected String image;

	public Position(int axisX, int axisY, TileType tile) {
		this.axisX = axisX;
		this.axisY = axisY;
		this.occupied = false;
		this.character = null;
		this.tile = tile;
		setImage();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getAxisX() {
		return this.axisX;
	}

	public int getAxisY() {
		return this.axisY;
	}

	public boolean isOccupied() {
		return this.occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character) {
		this.character = character;
		setImage();
	}

	public TileType getTile() {
		return this.tile;
	}

	public void setImage() {
		if (this.character != null) {
			this.image = this.character.getImage();
		} else {
			if (this.tile == TileType.WATER) {
				this.image = "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\water.png";
			} else if (this.tile == TileType.OBSTACLE) {
				this.image = "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\obstacle.png";
			} else if (this.tile == TileType.GRASS) {
				this.image = "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\grass.png";
			} else if (this.tile == TileType.MAIN_BASE1) {
				this.image = "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\mainBase.png";
			} else if (this.tile == TileType.MAIN_BASE2) {
				this.image = "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\mainBase.png";
			} else if (this.tile == TileType.BRIDGE) {
				this.image = "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\bridge.png";
			} else if (this.tile == TileType.SECONDARY_BASE) {
				this.image = "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\minorBase.png";
			}
		}
	}

	public String getImage() {
		return this.image;
	}

	public void setTile(TileType tile) {
		this.tile = tile;
		setImage();
	}

}
