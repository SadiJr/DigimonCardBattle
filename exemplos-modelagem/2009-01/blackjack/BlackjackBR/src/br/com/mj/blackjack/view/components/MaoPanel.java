package br.com.mj.blackjack.view.components;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class MaoPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1136618289787781608L;

	private List<CartaPanel> cartas;
	
	private boolean carteador = false;
	
	public MaoPanel(List<CartaPanel> cartas){
		this.cartas = cartas;
		ajustaTamanho();
		setLayout(null);
	    setOpaque(false);
	}
	
	public MaoPanel(){
		this.cartas = new ArrayList<CartaPanel>();
		ajustaTamanho();
		setLayout(null);
	    setOpaque(false);
	}
	
	public void ajustaTamanho(){
		Dimension size = null;
		
		if(carteador){
			size = new Dimension(160,80);
		}else{
			size = new Dimension(60,160);
		}
		
		setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    recarregaInterface();
	}
	
	public void setCarteador(boolean carteador){
		this.carteador = carteador;
		ajustaTamanho();
	}
	
	public boolean isCarteador(){
		return this.carteador;
	}
	
	public void recarregaInterface(){
		removeAll();
		int posX = 0;
		int posY = 0;
		for(CartaPanel painel : cartas){
			add(painel);
			painel.setBounds(posX,posY, painel.getWidth(), painel.getHeight());
			if(this.carteador){
				posX+=20;
			}else{
				posY+=20;
			}
			painel.viraCartaParaCima();
		}
	}

	public List<CartaPanel> getCartas() {
		return cartas;
	}

	public void setCartas(List<CartaPanel> cartas) {
		this.cartas = cartas;
	}
	
  
}
