package gui.images;

import javax.swing.ImageIcon;

public class ImagesHeader {
	
	protected ImageIcon zero, one, two, three, four, five, six, seven, eight, nine, daveLife,labelDave, labelScore, labelLevel, empty, empty2x;
	
	public ImagesHeader(){
		 this.zero = new ImageIcon(this.getClass().getResource("/imagens/0.gif"));
		 this.one = new ImageIcon(this.getClass().getResource("/imagens/1.gif"));
		 this.two = new ImageIcon(this.getClass().getResource("/imagens/2.gif"));
		 this.three = new ImageIcon(this.getClass().getResource("/imagens/3.gif"));
		 this.four = new ImageIcon(this.getClass().getResource("/imagens/4.gif"));
		 this.five = new ImageIcon(this.getClass().getResource("/imagens/5.gif"));
		 this.six = new ImageIcon(this.getClass().getResource("/imagens/6.gif"));
		 this.seven = new ImageIcon(this.getClass().getResource("/imagens/7.gif"));
		 this.eight = new ImageIcon(this.getClass().getResource("/imagens/8.gif"));
		 this.nine = new ImageIcon(this.getClass().getResource("/imagens/9.gif"));
		 this.daveLife = new ImageIcon(this.getClass().getResource("/imagens/daveLife.gif"));
		 this.labelDave = new ImageIcon(this.getClass().getResource("/imagens/daves.gif"));
		 this.labelScore = new ImageIcon(this.getClass().getResource("/imagens/score.gif"));
		 this.labelLevel = new ImageIcon(this.getClass().getResource("/imagens/level.gif"));
		 this.empty = new ImageIcon(this.getClass().getResource("/imagens/vazioPlac.gif"));
		 this.empty2x = new ImageIcon(this.getClass().getResource("/imagens/vazioPlac2.gif"));
	}
    public ImageIcon daveLife(){
    	return this.daveLife;
    
    }
    public ImageIcon labelDave(){
    	return this.labelDave;
    }
    public ImageIcon labelScore(){
    	return this.labelScore;
    }
    public ImageIcon labelLevel(){
    	return this.labelLevel;
    }
    public ImageIcon empty(){
    	return this.empty;
    }
    public ImageIcon empty2x(){
    	return this.empty2x;
    }
    
	public ImageIcon getNumber(int value){
    	switch (value) {
		case 0:
			return this.zero;
		case 1:
			return this.one;
		case 2:
			return this.two;
		case 3:
			return this.three;
		case 4:
			return this.four;
		case 5:
			return this.five;
		case 6:
			return this.six;
		case 7:
			return this.seven;
		case 8:
			return this.eight;
		case 9:
			return this.nine;
		default:
			break;
		}
		return this.zero;
    }
	
}
