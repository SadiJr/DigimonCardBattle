package gui;

import gui.images.ImagesMap;
import gui.sprites.GroundTile;
import gui.sprites.HideSprite;
import gui.sprites.PlayerSprite;
import gui.sprites.Shot;
import gui.sprites.Special;
import gui.sprites.Spider;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.MediaTracker;

import javax.swing.JPanel;

import com.genuts.gameui.AnimatedSprite;
import com.genuts.gameui.PlayField;
import com.genuts.gameui.Sprite;
import com.genuts.gameui.SpriteCollisionManager;

public class Map extends JPanel{
 
	private static final long serialVersionUID = 1L;
	protected int map;
	protected MediaTracker tracker;
	protected PlayField playfield;
	protected int actualLevel = 1;
	protected PlayerActor window;
	protected PlayerSprite playerSprite;
	protected ImagesMap img;
	protected PlayerDummy dave;
	
	public Map (){

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.setBackground(Color.black);
		this.img = new ImagesMap();
		
	} 
	
	public void makeMap(int[][] mapLevel, int[] values) {
		
		MediaTracker tracker = new MediaTracker(this);
	    for(int i=2;i<45;i++)
	    	tracker.addImage(this.img.getSprite(i), 0);
	    tracker.addImage(this.img.daveWalkLeft(), 0);
	    tracker.addImage(this.img.daveWalkRight(), 0);
	    tracker.addImage(this.img.getExp(), 0);
	    tracker.addImage(this.img.getShotDave(), 0);
	    tracker.addImage(this.img.getShotSpider(), 0);
	    
	    try {
	      tracker.waitForID(0);
	    } catch (InterruptedException e) {
	      System.out.println("Loading interrupted");
	    }
	   
	   SpriteCollisionManager collisionManager = new SpriteCollisionManager(50);
	   this.playfield = new PlayField(collisionManager, 1000, 555);
	   this.dave = new PlayerDummy(new PlayerSprite(new AnimatedSprite(this.img.daveWalkRight(), 3, 1, 2)), new PlayerSprite(new AnimatedSprite(this.img.daveWalkLeft(), 3, 1, 2)));
	   this.putSprites(mapLevel, values, false);
	   this.add(this.playfield);
	   this.pause(false);
	}
	 
	public void putSprites(int [][] mapLevel, int[] values, boolean back){
		this.playfield.removeAllSprites();
		    for (int y = mapLevel.length - 1; y >= 0; y--)
				for (int x = 0; x < mapLevel[y].length; x++) {
		    		Sprite sprite = null;
		    		if (mapLevel[y][x] != 0 && mapLevel[y][x] < 11 || mapLevel[y][x] == 43) {
		    			Sprite s = new HideSprite(new Sprite(this.img.getSprite(11)));
		    			s.setPosition(x*50+2, y*58-10);
		    			s.setId(mapLevel[y][x]);
		    			this.playfield.addSprite(s);
		    			sprite = new GroundTile(new Sprite(this.img.getSprite(mapLevel[y][x])));
		    		}
		    		else if (mapLevel[y][x] == 45){ 
		    			if(!back)
		    				sprite = this.dave;}
		    		else if (mapLevel[y][x] == 46) {
		    			if(back)
		    				sprite = this.dave;}
		    		else if (mapLevel[y][x] == 19)
	    				sprite = new Special(new AnimatedSprite(this.img.getSprite(mapLevel[y][x]),1,3,6), values[mapLevel[y][x]-16], y, x);
		    		else if (mapLevel[y][x] == 18 || mapLevel[y][x] == 20)
		    				sprite = new Special(new AnimatedSprite(this.img.getSprite(mapLevel[y][x]),3,1,6), values[mapLevel[y][x]-16], y, x);
		    		else if ( mapLevel[y][x] == 42 )
			    		sprite = new Special(new AnimatedSprite(this.img.getSprite(mapLevel[y][x]),4,1,4), values[mapLevel[y][x]-16], y, x);
		    		else if ( mapLevel[y][x] == 17 || mapLevel[y][x] == 16)
			    		sprite = new Special(new AnimatedSprite(this.img.getSprite(mapLevel[y][x]),2,1,4), values[mapLevel[y][x]-16], y, x);
		    		else if (mapLevel[y][x] == 37)
		    			sprite = new Spider(this, new Sprite(this.img.getSprite(mapLevel[y][x])), values[mapLevel[y][x]-16], y, x, new Shot(new Sprite(this.img.getShotSpider()), false));
		    		else if (mapLevel[y][x] != 0)
		    			sprite = new Special(new Sprite(this.img.getSprite(mapLevel[y][x])), values[mapLevel[y][x]-16], y, x);
		    		if (sprite!= null){
			    		sprite.setPosition(x*50, y*58);
		    			sprite.setId(mapLevel[y][x]);
		    			this.playfield.addSprite(sprite);
		    		}
		    	}

	}
	
	 
	public void pause(boolean pause) {
		this.playfield.setPause(pause);
				
	}
	
	public void removeSprite(Sprite s){
		  this.playfield.removeSprite(s);
	  }
	 
	public void restartLevel() {

	}
	 
	public void movePlayerRight() {
		this.dave.movePlayerRight();		
	}
	public void stopPlayerWalking() {
		this.dave.stopPlayerWalking();
	}
	public void movePlayerLeft() {
		this.dave.movePlayerLeft();
		
	}
	public void movePlayerFly() {
		this.dave.movePlayerFly();
		
	}

	public void movePlayerJump() {
		this.dave.movePlayerJump();
		
	}

	public void update(Graphics g) {
		super.update(g);
		this.playfield.update(this.playfield.getGraphics());
	}

	public PlayField getPlayfield() {
		return this.playfield;
	}

	public void explosion() {
		Sprite exp = new Special(new AnimatedSprite(this.img.getExp(), 3, 1, 2), 10, 2, 1);
		exp.setPosition(this.dave.getX(), this.dave.getY());
		this.playfield.removeSprite(this.dave);
		this.playfield.addSprite(exp);
		this.update(this.getGraphics());
	}

	public void shot() {
		Sprite shot = new Shot(new Sprite(this.img.getShotDave()),true);
		shot.setPosition(this.dave.getX()+ 55, this.dave.getY()+20);
		this.playfield.addSprite(shot);
		this.update(this.getGraphics());
	
	}

	public void removeSpider(Sprite spider,int x, int y) {
		Sprite exp = new Special(new AnimatedSprite(this.img.getExp(), 3, 1, 2), 10, 2, 1);
		exp.setPosition(x, y);
		this.playfield.removeSprite(spider);
		this.playfield.addSprite(exp);
		this.update(this.getGraphics());
		new Thread(new Runnable(){
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				Map.this.playfield.removeSprite(Map.this.playfield.getSpriteCount()-1);
			}				
		}).start();
	}

	public void shotSpider(Sprite spriteShot) {
		this.playfield.addSprite(spriteShot);
		
	}


	
	
	 
}
 
