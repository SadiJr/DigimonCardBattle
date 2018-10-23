package settlers.jogadas;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaMoverLadrao implements Jogada {
	
	private static final long serialVersionUID = 1L;
	
	private int camada;
	private int identificador;
	private int jogador;
	
	public JogadaMoverLadrao(int camada, int identificador, int jogador) {
		this.camada = camada;
		this.identificador = identificador;
		this.jogador = jogador;
	}

	public int getCamada() {
		return camada;
	}

	public int getIdentificador() {
		return identificador;
	}

	public int getJogador() {
		return jogador;
	}
	
}
