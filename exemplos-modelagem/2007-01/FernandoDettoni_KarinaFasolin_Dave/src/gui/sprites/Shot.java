package gui.sprites;

import com.genuts.gameui.MovingSpriteWrapper;
import com.genuts.gameui.Sprite;


public class Shot extends MovingSpriteWrapper  {
	
		boolean right;
		
			public Shot(Sprite sprite, boolean right) {
			super(sprite);
			this.right=right;
		}

			public void move(int tick) {
				if(this.right)
					this.setPosition(this.getX() + 10, this.getY());
				else
					this.setPosition(this.getX() - 10, this.getY());
			}
}
