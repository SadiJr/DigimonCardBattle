package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Help  extends JPanel{
 
BufferedImage help;
int atual = 1;
	
	public Help(){
		this.setBackground(Color.BLACK);
		//this.setPreferredSize(new Dimension(1000, 740));
		try {
			this.help = ImageIO.read(this.getClass().getResource("/imagens/help0"+new Integer(this.atual).toString()+"es.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics arg0) {
		super.paint(arg0);
		arg0.drawImage(this.help, 0, 0, null);
	} 
	
	public boolean update(){
		this.atual++;
		if(this.atual>3)
			return false;
		try {
			this.help = ImageIO.read(this.getClass().getResource("/imagens/help0"+new Integer(this.atual).toString()+"es.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.update(this.getGraphics());
		return true;
	}
	
}
 
