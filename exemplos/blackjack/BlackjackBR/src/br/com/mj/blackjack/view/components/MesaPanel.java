package br.com.mj.blackjack.view.components;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class MesaPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6083206527806652896L;
	private Color bgColor;
	private int height = 400;
	private int width = 600;
	private final Image caixa;
	
	public MesaPanel(){
		bgColor = new Color(20,100,70);
		caixa = new ImageIcon("images/fichas/caixa_fichas.png").getImage();
		this.setLayout(new FlowLayout());
	}

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawString("Jhonatan", 0, 100);
		g2d.draw3DRect(50,50, 100, 100, true);
		g2d.setColor(bgColor);
		g2d.fillRoundRect(0, 0, width, height, 10, 10);
		desenhaLugarJogador(g2d, 1);
		desenhaLugarJogador(g2d, 2);
		desenhaLugarJogador(g2d, 3);
		desenhaLugarJogador(g2d, 4);
		desenhaBlackjack(g2d);
		g2d.rotate(Math.toRadians(50));
		g2d.drawImage(caixa,170,0,null);
		g2d.drawOval(-50, -400, 600, 600);
		g2d.drawOval(-62, -400, 625, 625);
		//paintComponents(g);
		
	}
	public void desenhaBlackjack(Graphics2D g2d){
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial",Font.BOLD,30));
		g2d.rotate(Math.toRadians(50));
		g2d.drawString("B", 170, -40);
		g2d.rotate(Math.toRadians(-10));
		g2d.drawString("L", 205, -5);
		g2d.rotate(Math.toRadians(-12));
		g2d.drawString("A", 225, 45);
		g2d.rotate(Math.toRadians(-16));
		g2d.drawString("C", 235, 115);
		g2d.rotate(Math.toRadians(-12));
		g2d.drawString("K", 240, 168);
		g2d.rotate(Math.toRadians(-16));
		g2d.drawString("J", 220, 235);
		g2d.rotate(Math.toRadians(-12));
		g2d.drawString("A", 190, 280);
		g2d.rotate(Math.toRadians(-12));
		g2d.drawString("C", 160, 315);
		g2d.rotate(Math.toRadians(-10));
		g2d.drawString("K", 140, 340);
	}
	
	public void desenhaLugarJogador(Graphics2D g2d,int numJogador){
		g2d.setColor(Color.WHITE);
		switch(numJogador){
			case 1:
				g2d.drawOval(50, this.height-100, 60, 60);
				break;
			case 2:
				g2d.drawOval(160, this.height-100, 60, 60);
				break;
			case 3:
				g2d.drawOval(270, this.height-100, 60, 60);
				break;
			case 4:
				g2d.drawOval(380, this.height-100, 60, 60);
				break;
		}
	}
}
