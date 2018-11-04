package model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Lance implements Jogada {

	protected Carta carta;
	protected Jogador jogador;
	protected TipoLance tipoLance;

	public Lance(Carta carta, Jogador jogador) {
		super();
		this.carta = carta;
		this.jogador = jogador;
	}

	public Lance() {
		super();
	}
	
	public enum TipoLance {
		COMPRAR_CARTA,
		JOGAR_CARTA
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	public TipoLance getTipoLance() {
		return tipoLance;
	}
	
	public void setTipoLance(TipoLance tipoLance) {
		this.tipoLance = tipoLance;
	}
}
