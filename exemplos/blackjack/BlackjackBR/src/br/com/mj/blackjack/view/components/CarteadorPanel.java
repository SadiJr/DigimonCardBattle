package br.com.mj.blackjack.view.components;

import java.awt.Dimension;

import br.com.mj.blackjack.model.Jogador;

public class CarteadorPanel extends JogadorPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7411928149253169948L;

	public CarteadorPanel(Jogador carteador) {
		super(carteador);
		Dimension size = new Dimension(250,120);
		setTamanho(size);
		mao.setCarteador(true);
		mao.recarregaInterface();
		mao.setBounds(0,25, mao.getWidth(), mao.getHeight());
		
		nomeJogador.setText("Carteador");
		nomeJogador.setBounds(150,0,100,100);
		pontos.setBounds(150,15,100,100);
	}

}
