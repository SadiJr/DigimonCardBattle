package gui.sprites;

import gui.Map;

import com.genuts.gameui.MovingSpriteWrapper;
import com.genuts.gameui.Sprite;



public class Spider extends MovingSpriteWrapper  {

	protected int i, j, value, shot = 0;
	protected int rX, rY, h = 0; 
	protected Sprite spriteShot;
	protected Map map;
	
	public Spider(Sprite sprite) {
		super(sprite);
	}
		
	public Spider(Map map,Sprite sprite, int value, int x, int y, Sprite shot) {
		super(sprite);
		this.value = value;
		this.i = x;
		this.j = y;
		this.map = map;
		this.spriteShot = shot;
		}

		public void move(int tick) {
			switch(this.h){
			case 0:
				if(this.getX()<this.rX)
					super.setPosition(this.getX()+10, this.getY()-6);
				else
					this.h=1;
				break;
			case 1:
				if(this.getX()<(this.rX+100))
					super.setPosition(this.getX()+10, this.getY()+6);
				else
					this.h=2;
				break;
			case 2:
				if(this.getX()>(this.rX))
					super.setPosition(this.getX()-10, this.getY()+6);
				else
					this.h=3;
				break;
			case 3:
				if(this.getX()>(this.rX-100))
					super.setPosition(this.getX()-10, this.getY()-6);
				else
					this.h=0;
				break;
			}
			this.shot++;
			if(this.shot>=70){
				this.spriteShot = new Shot(new Sprite(this.spriteShot.getImage()), false);
				this.spriteShot.setPosition(this.getX()-50, this.getY());
				this.map.shotSpider(this.spriteShot);
				this.shot = 0;
			}
				
					
		}

		public boolean setPosition(int arg0, int arg1) {
			this.rX = arg0+100;
			this.rY = arg1+58; 
			return super.setPosition(arg0, arg1);
		}
		
		public void collisionWith(Sprite sprite) {
			if (sprite instanceof Shot)
				this.map.removeSpider(this,this.getX(),this.getY());
		}
		
}
