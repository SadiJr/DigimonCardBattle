package br.com.mj.blackjack.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.mj.blackjack.model.Carta;
import br.com.mj.blackjack.model.Jogador;

public class JogadorPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8995367604042999292L;

	protected JLabel nomeJogador;
	
	protected JLabel pontos;
	
	private Jogador jogador;
	
	protected MaoPanel mao;
	
	public JogadorPanel(Jogador jogador){
		this.jogador = jogador;
		this.mao = new MaoPanel();
		this.nomeJogador = new JLabel(jogador.getNome());
		this.nomeJogador.setForeground(Color.WHITE);
		this.pontos = new JLabel();
		this.pontos.setForeground(Color.WHITE);
		this.setLayout(null);
		Dimension size = new Dimension(100,250);
		setTamanho(size);
		this.add(mao);
		mao.setBounds(0, 0, mao.getWidth(), mao.getHeight());
		this.add(nomeJogador);
		nomeJogador.setBounds(0,130,100,100);
		this.add(pontos);
		pontos.setBounds(0,150,100,100);
		this.setOpaque(false);
		atualiza();
	}
	
	public void atualiza(){
		List<Carta> cartas = jogador.getCartasDaMao();
		List<CartaPanel> panelCartas = mao.getCartas();
		
		for(Carta carta : cartas){
			CartaPanel panel = new CartaPanel(carta);
			if(!panelCartas.contains(panel)){
				panelCartas.add(panel);
			}
		}
		mao.recarregaInterface();
		
		if(jogador.isBlackjack()){
			pontos.setText("Blackjack");
		}else if(jogador.isCincoCartasCharlie()){
			pontos.setText("Cinco Cartas Charlie");
		}else{
			pontos.setText(""+jogador.getValorDaMao()+ (jogador.isFora() ?". Está fora!":""));
		}
	}
	
	public void setTamanho(Dimension size){
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setSize(size);
	}
	
	public MaoPanel getMao() {
		return mao;
	}

	public void setMao(MaoPanel mao) {
		this.mao = mao;
	}
}
