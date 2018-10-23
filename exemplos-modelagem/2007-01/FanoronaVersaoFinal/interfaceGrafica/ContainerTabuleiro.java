package interfaceGrafica;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ContainerTabuleiro extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Desenha o tabuleiro (linhas horizontais, verticais e diagonais)
	 * 
	 * */	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.gray);
		//horizontais
		g.drawLine(40, 60, 600, 60);
		g.drawLine(40, 130, 600, 130);
		g.drawLine(40, 200, 600, 200);
		g.drawLine(40, 270, 600, 270);
		g.drawLine(40, 340, 600, 340);
		
		//verticais
		g.drawLine(40, 60, 40, 340);
		g.drawLine(110, 60, 110, 340);
		g.drawLine(180, 60, 180, 340);
		g.drawLine(250, 60, 250, 340);
		g.drawLine(320, 60, 320, 340);
		g.drawLine(390, 60, 390, 340);
		g.drawLine(460, 60, 460, 340);
		g.drawLine(530, 60, 530, 340);
		g.drawLine(600, 60, 600, 340);
		
		//diagonais superiores
		g.drawLine(40, 60, 180, 200);
		g.drawLine(40, 200, 180, 60);
		
		g.drawLine(180, 60, 320, 200);
		g.drawLine(180, 200, 320, 60);
		
		g.drawLine(320, 60, 460, 200);
		g.drawLine(320, 200, 460, 60);
		
		g.drawLine(460, 60, 600, 200);
		g.drawLine(460, 200, 600, 60);
		
		//diagonais inferiores
		g.drawLine(40, 200, 180, 340);
		g.drawLine(40, 340, 180, 200);
		
		g.drawLine(180, 200, 320, 340);
		g.drawLine(180, 340, 320, 200);
		
		g.drawLine(320, 200, 460, 340);
		g.drawLine(320, 340, 460, 200);
		
		g.drawLine(460, 200, 600, 340);
		g.drawLine(460, 340, 600, 200);
	}

}
