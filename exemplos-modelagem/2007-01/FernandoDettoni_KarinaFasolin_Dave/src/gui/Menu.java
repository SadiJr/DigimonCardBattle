package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Menu extends JPanel{
	
	BufferedImage mnu;
	
	public Menu(){
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(1000, 740));
		try {
			this.mnu = ImageIO.read(this.getClass().getResource("/imagens/menu.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);
		arg0.drawImage(this.mnu, 0, 0, null);
		
	}
	
 
	 
}
 
