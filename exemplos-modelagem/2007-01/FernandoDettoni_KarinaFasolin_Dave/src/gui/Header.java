package gui;

import gui.images.ImagesHeader;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Header extends JPanel{
 
	protected JLabel[] headerArray;
	protected ImageIcon[] images;
	protected ImagesHeader img;
	protected int lifes = 3;
	
	public Header(){
		super();
		this.img = new ImagesHeader();
		this.setPreferredSize(new Dimension(1010, 58));
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 0, 0);
		this.setLayout(layout);
		this.headerArray = new JLabel[21];
		this.images = new ImageIcon[21];
		this.images[0] = this.img.labelScore();
		this.images[1] = this.img.empty();
		this.images[2] = this.img.getNumber(0);
		this.images[3] = this.img.getNumber(0);
		this.images[4] = this.img.getNumber(0);
		this.images[5] = this.img.getNumber(0);
		this.images[6] = this.img.getNumber(0);
		this.images[7] = this.img.empty();	
		this.images[8] = this.img.empty();
		this.images[9] = this.img.labelLevel();	
		this.images[10] = this.img.empty();
		this.images[11] = this.img.getNumber(0);
		this.images[12] = this.img.getNumber(1);
		this.images[13] = this.img.empty();
		this.images[14] = this.img.labelDave();
		this.images[15] = this.img.daveLife();
		this.images[16] = this.img.daveLife();
		this.images[17] = this.img.daveLife();
		this.images[18] = this.img.empty();


		
		
		for(int i = 0; i<21; i++){
			this.headerArray[i] = new JLabel(this.images[i]);
			this.add(this.headerArray[i]);
		}
	}
	public void updateScore(int score) {
		String sScore = new Integer (score).toString();
		for(int i=sScore.length(); i<5; i++)
			sScore = "0"+sScore;

		for(int i = 6, j=sScore.length()-1; i>=2 && j>=0; --i, --j){
			this.remove(this.headerArray[i]);
			
			this.images[i] = this.img.getNumber(Character.valueOf(sScore.charAt(j))-48);
			this.headerArray[i] = new JLabel(this.images[i]);
			
			this.add(this.headerArray[i], i);		
		}
		this.update(this.getGraphics());
	}
	
	public void updateLevel(int level) {
		this.headerArray[12].setIcon(this.img.getNumber(level));
		this.update(this.getGraphics());
	}
	 
	public void updateLifes(int lifes) {

		for(int i=15; i<18; i++){
			this.remove(this.headerArray[i]);
			if(lifes-->0){
				this.headerArray[i] = new JLabel(this.img.daveLife());				
				this.add(this.headerArray[i], i);
			} else {
				this.headerArray[i] = new JLabel(this.img.empty2x());				
				this.add(this.headerArray[i], i);
			}
		}
		this.update(this.getGraphics());
	}
	 
}
 
