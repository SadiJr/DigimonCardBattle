package view;

import control.ControladorMesa;
import model.Carta;
import model.Jogador;

public class AtorJogador {
	
	protected JMesa jMesa;
	protected ControladorMesa controladorMesa;

	public AtorJogador(JMesa jMesa) {
		this.controladorMesa = new ControladorMesa(jMesa);
	}
	
	public ControladorMesa getControladorMesa() {
		return controladorMesa;
	}
	
	public Jogador getJogadorAtual() {
        return controladorMesa.getJogadorAtual();
    }

    public void setJogadorAtual(Jogador jogador) {
    	controladorMesa.setJogadorAtual(jogador);
    }
	
	public void setControladorMesa(ControladorMesa controladorMesa) {
		this.controladorMesa = controladorMesa;
	}
	
	public boolean conectar(String nomeJogador, String hostServidor) {
		return controladorMesa.conectarRede(nomeJogador, hostServidor);
	}
	
	public void desconectar() {
		controladorMesa.desconectarRede();
	}
	
	public void iniciarPartida() {
		controladorMesa.iniciarPartida();
	}
	
	public boolean comprarCarta(Jogador jogando) {
		return controladorMesa.comprarCarta(jogando);
	}
	
	public boolean jogarCarta(Carta carta) {
		return controladorMesa.jogarCarta(carta);
	}
}
