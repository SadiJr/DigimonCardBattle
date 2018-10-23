package gui;

import gui.images.ImagesFooter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class Footer extends JPanel{
 
	protected ImagesFooter img;
	protected ArrayList<Paint> paints;
	
	 
	public Footer(){
		this.setPreferredSize(new Dimension(1000,105));
		this.setBackground(Color.BLACK);
		this.img = new ImagesFooter();
		this.paints = new ArrayList<Paint>();
		
		//paints.remove(new Paint(null, 227, 58));
		
	}
	public void informTrophy(boolean have) {
		if(have)
			this.paints.add(new Paint(this.img.getWin(), 227, 50));
		else
			this.paints.remove(new Paint(null, 227,50));
		this.update(this.getGraphics());
	}
	 
	public void setAcessories(int acessories) {
		if(acessories ==2)
			this.paints.add(new Paint(this.img.getGun2(), 730, 0));
		else if(acessories == 1){//JetPack
			this.paints.add(new Paint(this.img.getJetpackPlac(), 30, 0));		
			for(int i = 270; i<650; i+=6)
				this.paints.add(new Paint(this.img.getJetpackFuel(), i, 16));
		} else if(acessories ==0){
			this.paints.remove(new Paint(null, 730, 0));
			this.paints.remove(new Paint(null, 30, 0));
		}
		this.update(this.getGraphics());
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Iterator<Paint> i = this.paints.iterator();
		while(i.hasNext()){
			Paint p = i.next();
			g.drawImage(p.image, p.x, p.y, null);
		}
	}

	protected class Paint {
		BufferedImage image;
		int x;
		int y;
		public Paint(BufferedImage image, int x, int y){
			this.image = image;
			this.x = x;
			this.y = y;			
		}

	public boolean equals(Object obj) {
		if(obj instanceof Paint){
			Paint ob = (Paint)obj;
			if(ob.x==this.x && ob.y==this.y)
				return true;
		}
		return false;
			
		}
	}
}
 
