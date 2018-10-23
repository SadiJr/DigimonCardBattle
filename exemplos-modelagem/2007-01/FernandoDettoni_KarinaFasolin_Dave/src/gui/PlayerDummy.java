package gui;

import gui.sprites.PlayerSprite;

import java.awt.Point;

import com.genuts.gameui.PlayField;
import com.genuts.gameui.Sprite;
import com.genuts.gameui.SpriteWrapper;

public class PlayerDummy extends SpriteWrapper {
	protected PlayerSprite daveRight;
	protected PlayerSprite daveLeft;
	protected int keyAction = 0;
	protected static int goLeft = 1;
	protected static int goRight = 2;
	protected static int goUp = 1<<2;
	  
	public PlayerDummy(PlayerSprite daveRight, PlayerSprite daveLeft){
		    super(daveRight);
		    this.daveRight = daveRight;
		    this.daveLeft = daveLeft;
		    this.daveLeft.setLeftSpeed();
	}
	protected void changeDirection(PlayerSprite fromSprite, PlayerSprite toSprite) {
	    PlayField parent = this.getParent();
	    Sprite finalWrapper = this.getFinalWrapper();
	    Point p = this.getPosition();
	    toSprite.setCurrentPictureNumber(fromSprite.getCurrentPictureNumber());
	    parent.removeSprite(finalWrapper);
	    this.setActionSprite(toSprite);
	    this.setPosition(p.x, p.y);
	    parent.addSprite(finalWrapper);
	}
	public void movePlayerLeft() {
	    this.keyAction &= ~goRight;
	    this.keyAction |= goLeft;
		if (this.getActionSprite() != this.daveLeft)
			this.changeDirection(this.daveRight, this.daveLeft);
	    this.daveLeft.walk(true);
	}
	public void movePlayerRight() {
	    this.keyAction &= ~goLeft;
	    this.keyAction |= goRight;
		if (this.getActionSprite() != this.daveRight)
			this.changeDirection(this.daveLeft, this.daveRight);
	    this.daveRight.walk(true);
	}
	public void stopPlayerWalking() {
		((PlayerSprite) this.getActionSprite()).walk(false);
	}
	public void movePlayerJump() {
		this.keyAction |= goUp;
	    ((PlayerSprite) this.getActionSprite()).movePlayerJump(true);
	}
	public void movePlayerFly() {
		this.keyAction |= goUp;
	    ((PlayerSprite) this.getActionSprite()).movePlayerFly(true);
		
	}
}
