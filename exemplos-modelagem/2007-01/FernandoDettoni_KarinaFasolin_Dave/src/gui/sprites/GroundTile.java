package gui.sprites;

import com.genuts.gameui.Sprite;
import com.genuts.gameui.SpriteWrapper;

public class GroundTile extends SpriteWrapper {

	  public GroundTile(Sprite sprite) {
	    super(sprite);
	  }

	  public boolean preCollisionWith(Sprite s) {
	    return false;
	  }
}