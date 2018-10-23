package gui.images;

import java.awt.Image;
import java.awt.Toolkit;

public class ImagesMap {
	
	protected Image tile, tile2, tile3, tile4, tile5, tile6, tile7, tile8,tile9,tile10, tile11;
	protected Image daveWalkLeft, daveWalkRight;
	protected Image redDiam, blueDiam, door, pipe, trofeu, fire, fire2, leaf, gun,ring, jet, ball, crown, pin, black,exp, water,water2, shotDave, shotSpider, spider;
	
	
	
	
	public ImagesMap(){
		this.tile = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/tile1.gif"));
		this.tile2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/tile2.gif"));
		this.tile3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/tile3.gif"));
		this.tile4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/tile4.gif"));
		this.tile5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/tile5.gif"));
		this.tile6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/tile2inf.gif"));
		this.tile7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/tile7.gif"));
		this.tile8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/tile8.gif"));
		this.tile9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/tile9.gif"));
		this.tile10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/tile10.gif"));
		this.tile11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/tile11.gif"));
	
		this.daveWalkLeft = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/daves_left.gif"));
	    this.daveWalkRight = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/daves_right.gif"));
	    this.spider = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/spider.gif"));
	    this.fire = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/fire.gif"));
	    this.fire2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/fire2.gif"));
	    this.leaf = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/leaf2.gif"));
	    this.water = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/water.gif"));
	    this.water2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/water2.gif"));
	    this.exp = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/explosion.gif"));
	    this.shotDave =  Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/daveshot.gif"));
	    this.shotSpider =  Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/spidershot.gif"));
	    
	    this.crown = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/crown.gif"));
	    this.pin = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/pin.gif"));
	    this.redDiam = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/redDiam.gif"));
	    this.blueDiam = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/blueDiam.gif"));
	    this.ring = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/ring.gif"));
	    this.ball = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/ball.gif"));
	    
	    this.pipe = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/pipe.gif"));
	    this.door = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/door.gif"));
	    this.trofeu = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/trofeu.gif"));
	    this.gun = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/gun.gif"));
	    this.jet = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/jetpack.gif"));
	    
	    this.black = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagens/black.gif"));
	 }
	
	
    public Image getSprite(int item){
    	switch (item) {
		
    	case 1:
			return this.tile;
		case 2:
			return this.tile2;
		case 3:
			return this.tile3;
		case 4:
			return this.tile4;
		case 5:
			return this.tile5;
		case 6:
			return this.tile6; 
		case 7:
			return this.tile7;
		case 8:
			return this.tile8;
		case 9:
			return this.tile9;
		case 10:
			return this.tile10;
		case 11:
			return this.tile11;
		case 16:
			return this.water;
		case 17:
			return this.water2;
		case 18:
			return this.fire;//-1
		case 19:
			return this.leaf;//-1
		case 20:
			return this.fire2;//-1
		case 21:
			return this.jet; // 1
		case 22:
			return this.gun;	 //2
		case 23:
			return this.ring;//200
		case 24:
			return this.blueDiam;//100
		case 25:
			return this.redDiam;//150
		case 26:
			return this.ball;//50
		case 27:
			return this.crown;
		case 28:
			return this.pin;
		case 37:
			return this.spider;
		case 38:
			return this.black;
		case 39:
			return this.black;
		case 40:
			return this.black;	
		case 41:
			return this.door;//2000	
		case 42:
			return this.trofeu;//1000
		case 43:
			return this.pipe;
		default:
			return this.tile;
		}
    }
    
    public Image daveWalkLeft(){
    	return this.daveWalkLeft;
    }
    public Image daveWalkRight(){
    	return this.daveWalkRight;
    }
    public Image getExp(){
    	return this.exp;
    }
    public Image getShotDave(){
    	return this.shotDave;
    }
    public Image getShotSpider(){
    	return this.shotSpider;
    }

    


}

    