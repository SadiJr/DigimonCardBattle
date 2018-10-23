package settlers.jogadas;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaAceitarTroca implements Jogada {

	private static final long serialVersionUID = 1L;

	private int jogadorOrigem;
	private int jogadorDestino;
	private int oferta[];
	private int procura[];
	
	public JogadaAceitarTroca(int jogadorOrigem, int jogadorDestino, int oferta[], int procura[]) {
		this.jogadorOrigem = jogadorOrigem;
		this.jogadorDestino = jogadorDestino;
		this.oferta = oferta;
		this.procura = procura;
	}
	
	public int getJogadorOrigem() {
		return jogadorOrigem;
	}
	
	public int getJogadorDestino() {
		return jogadorDestino;
	}
	
	public int[] getOferta() {
		return oferta;
	}
	
	public int[] getProcura() {
		return procura;
	}

}
