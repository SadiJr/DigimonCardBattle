package game;

import enums.CharacterType;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Character implements Jogada {

	protected CharacterType type;
	protected boolean attacked;
	protected boolean moved;
	protected boolean selected;
	protected String image;

	public Character(CharacterType type) {
		this.type = type;
		this.attacked = false;
		this.moved = false;
		this.selected = false;
		setImage();
	}

	public CharacterType getType() {
		return type;
	}

	public void setType(CharacterType type) {
		this.type = type;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getImage() {
		return image;
	}

	public void setImage() {
		// if (this.player.getName() == "Player 1") {
		if (this.type == CharacterType.RIFLEMAN) {
			this.image = "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\rifleman1.png";
		} else if (this.type == CharacterType.SUBMACHINE) {
			this.image = "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\sub1.png";
		} else if (this.type == CharacterType.SCOUT) {
			this.image = "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\scout1.png";
		} else if (this.type == CharacterType.SNIPER) {
			this.image = "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\sniper1.png";
		}
	} /*
		 * else if (this.player.getName() == "Player 2"){ if (this.getType() ==
		 * CharacterType.RIFLEMAN) { System.out.println("rif 2"); this.image =
		 * "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\rifleman2.png"; }
		 * else if (this.getType() == CharacterType.SUBMACHINE) { this.image =
		 * "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\sub2.png"; } else
		 * if (this.getType() == CharacterType.SCOUT) { this.image =
		 * "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\scout2.png"; } else
		 * if (this.getType() == CharacterType.SNIPER) { this.image =
		 * "C:\\Users\\Jean Leopoldo\\Desktop\\battleRoyale\\images\\sniper2.png"; } } }
		 */

}
