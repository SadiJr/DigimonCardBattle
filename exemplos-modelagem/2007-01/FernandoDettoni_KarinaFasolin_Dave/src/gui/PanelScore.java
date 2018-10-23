package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelScore extends JPanel {
	String[][] values;
	BufferedImage duke;
	
	public PanelScore(String[][] values){
		this.setPreferredSize(new Dimension(1000, 555));
		this.setBackground(Color.WHITE);
		this.values = values;
		try {
			this.duke = ImageIO.read(this.getClass().getResource("/imagens/fight.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(10f));
		g2.drawRect(10, 10, 980, 525);
		g2.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
		g2.drawImage(this.duke, null, 200, 20);
		g2.drawString("Game Over", 370, 60);
		g2.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		for(int i =100; i<this.values[0].length*40+100; i+=40)
			if(this.values[0][(i-100)/40]!=null)
				g2.drawString(this.values[0][(i-100)/40]+":   "+this.values[1][(i-100)/40], 30, i);
		g2.drawString("*******Press F3 key to restart or ESC key to exit*******", 120, 520);
		
				
		
	}
	
	

}
