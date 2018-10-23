package gui.sprites;

import app.GamePlay;

import com.genuts.gameui.AnimatedSprite;
import com.genuts.gameui.MovingSpriteWrapper;
import com.genuts.gameui.Sprite;

public class PlayerSprite extends MovingSpriteWrapper{
  
	protected boolean jumping = false;
	protected boolean flying = false;
	protected int jump =0;
	protected int hSpeed = 6;
	protected int vSpeed = 10;
	protected boolean walking = false;
	protected GamePlay gamePlay;
	
	public PlayerSprite(AnimatedSprite dave) {
		super(dave);
		this.setPremode(true);
		dave.setPause(true);
		this.gamePlay = GamePlay.getInstance();
	}
	public int getVerticalSpeed() {
	    return this.vSpeed;
	}
	public int getHorizontalSpeed() {
	    return this.hSpeed;
	}
	public void collisionWith(Sprite sprite) {
		if (sprite instanceof Shot)
			this.gamePlay.explosion();
		if (sprite instanceof HideSprite && this.jumping){
			this.jump = 0;
			this.movePlayerJump(false);
		}
		if(sprite instanceof Special){			
			if (sprite.getId() >= 23 &&  sprite.getId()<=28) {
			    this.gamePlay.updateScore(((Special)sprite).getValue());
			    this.gamePlay.removeSprite(sprite);
			}
		    else if (sprite.getId() >= 18 &&  sprite.getId()<=20)
		    	this.gamePlay.explosion();
		    else if(sprite.getId() == 42){
		    	this.gamePlay.updateTrophy(true);
		    	this.gamePlay.updateScore(((Special)sprite).getValue());
		    	this.gamePlay.removeSprite(sprite);
		    }
		    else if(sprite.getId()== 41) //door
		    	this.gamePlay.passLevel();
		    else if (sprite.getId() == 21 || sprite.getId() == 22){
		    	this.gamePlay.updateAcessories(sprite.getId()-20);
		    	this.gamePlay.removeSprite(sprite);
		    }
		    else if (sprite.getId()==40)//proxima tela
		    	this.gamePlay.updateScreen();
		    else if (sprite.getId()==38)//tela anterior
		    	this.gamePlay.backScreen();
		    else if (sprite.getId()==39)//final do pass
		    	this.gamePlay.updateLevel();
		} else 
		super.collisionWith(sprite);
	
	}
	

	public void move(int tick) {
		if (this.isJumping() && this.jump < 120) {
			((AnimatedSprite) this.getActionSprite()).setPause(true);
		    this.setPosition(this.getX(), this.getY() - this.getVerticalSpeed());
		    this.jump+=10;
		}else if (this.setPosition(this.getX(), this.getY() + this.getVerticalSpeed()))
			((AnimatedSprite) this.getActionSprite()).setPause(true);
		else if (this.isWalking())
			((AnimatedSprite) this.getActionSprite()).setPause(false);
	    if (this.isWalking())
			this.setPosition(this.getX() + this.getHorizontalSpeed(), this.getY());
		else
			((AnimatedSprite) this.getActionSprite()).setPause(true);
		  
	}

	public int getCurrentPictureNumber() {
	    return ((AnimatedSprite) this.getActionSprite()).getCurrentPictureNumber();
	}
	public void setCurrentPictureNumber(int n) {
	    ((AnimatedSprite) this.getActionSprite()).setCurrentPictureNumber(n);
	}
	public void walk(boolean walk){
		this.walking = walk;		
	}
	public void movePlayerJump(boolean jump){
		if(jump)
			if(this.hSpeed<0)
				this.hSpeed = -12;
			else
				this.hSpeed= 12;
		else
			if(this.hSpeed<0)
				this.hSpeed = -6;
			else
				this.hSpeed =6;
		
		this.jumping = jump;
	}
	public void movePlayerFly(boolean fly){
		this.flying = fly;
	}
	public boolean isJumping() {
		return this.jumping;
	}
	public boolean isWalking() {
	    return this.walking;
	}
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	public void setJump(int jump) {
		this.jump = jump;
	}

	public void setLeftSpeed() {
		this.hSpeed = -Math.abs(this.hSpeed);
	}

	public boolean isFlying() {
		return this.flying;
	}

	 
}
 
