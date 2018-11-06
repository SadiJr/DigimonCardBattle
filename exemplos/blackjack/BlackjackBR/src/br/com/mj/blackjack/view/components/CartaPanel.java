package br.com.mj.blackjack.view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import br.com.mj.blackjack.model.Carta;

public class CartaPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6116710626772859386L;
	//A imagem da carta virada para baixo, é igual para todas!
	private final static Image costas;
	//inicializa a imagem
	static{
		costas = new ImageIcon("images/baralho/back.png").getImage();
	}
	
	private final Carta carta;
	private final Image frente;
	private boolean viradaParaBaixo;
	
	public CartaPanel(Carta carta){
		this.carta = carta;
		this.viradaParaBaixo = true;
		setOpaque(false);
		
		String nomeImg = String.format("images/baralho/%s_%s.png",carta.getNumero(),carta.getNaipe());
		System.out.println(nomeImg);
		this.frente = new ImageIcon(nomeImg).getImage();
		
		Dimension size = new Dimension(CartaPanel.costas.getWidth(null),CartaPanel.costas.getHeight(null));
		setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	}
	
	public void paint(Graphics g){
		if(this.viradaParaBaixo){
			g.drawImage(costas, 0,0,null);
		}else{
			g.drawImage(frente, 0,0,null);
		}
	}
	
	public boolean isViradaParaBaixo(){
		return this.viradaParaBaixo;
	}
	
	public void viraCartaParaCima(){
		this.viradaParaBaixo = false;
		repaint();
	}
	
	public void viraCaraParaBaixo(){
		this.viradaParaBaixo = true;
		repaint();
	}

	public Carta getCarta() {
		return carta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carta == null) ? 0 : carta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaPanel other = (CartaPanel) obj;
		if (carta == null) {
			if (other.carta != null)
				return false;
		} else if (!carta.equals(other.carta))
			return false;
		return true;
	}
	
	
}
