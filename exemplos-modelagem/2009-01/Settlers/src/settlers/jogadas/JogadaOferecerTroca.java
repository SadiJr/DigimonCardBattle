package settlers.jogadas;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaOferecerTroca implements Jogada {

	private static final long serialVersionUID = 1L;

	private int jogador;
	private int oferta[];
	private int procura[];
	
	public JogadaOferecerTroca(int jogador, int oferta[], int procura[]) {
		this.jogador = jogador;
		this.oferta = oferta;
		this.procura = procura;
	}
	
	public int getJogador() {
		return jogador;
	}
	
	public int[] getOferta() {
		return oferta;
	}
	
	public int[] getProcura() {
		return procura;
	}

}
