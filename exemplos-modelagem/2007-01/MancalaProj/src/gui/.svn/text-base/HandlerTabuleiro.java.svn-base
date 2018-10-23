package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.ControladorTabuleiro;
import app.Jogador;

public class HandlerTabuleiro implements ActionListener {
	
	protected Jogador jogador;
	protected ControladorTabuleiro controle;
	protected AtorJogador painelTab;
	
	public HandlerTabuleiro(AtorJogador painelTab, ControladorTabuleiro controle){
		this.controle = controle;
		this.painelTab = painelTab;
	}
	
	public void actionPerformed(ActionEvent e) {		
			int opt = Integer.parseInt(e.getActionCommand());
			jogador = controle.getJogadorDaVez();
			jogador.definirCasaJogada(opt);
			controle.pedirJogada(jogador);
			painelTab.tratarJogada(controle.getEstadoAtual());
		}

}

