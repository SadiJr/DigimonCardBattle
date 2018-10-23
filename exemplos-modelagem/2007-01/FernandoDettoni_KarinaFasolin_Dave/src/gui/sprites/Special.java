package gui.sprites;
import com.genuts.gameui.Sprite;
import com.genuts.gameui.SpriteWrapper;

public class Special extends SpriteWrapper  {
	
	protected int value;
	protected int i;
	protected int j;

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Special(Sprite sprite, int value, int x, int y) {
		super(sprite);
		this.value = value;
		this.i = x;
		this.j = y;
	}

	public int getI() {
		return this.i;
	}

	public int getJ() {
		return this.j;
	}

}
