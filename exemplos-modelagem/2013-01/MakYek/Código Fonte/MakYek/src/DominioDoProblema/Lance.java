package DominioDoProblema;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Lance implements Jogada {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5460712200985589059L;

	protected int linhaOrigem;
	protected int colunaOrigem;
	protected int linhaDestino;
	protected int colunaDestino;

	public void informaPosicaoOrigem(int linhaOrigem, int colunaOrigem) {
		this.linhaOrigem = linhaOrigem;
		this.colunaOrigem = colunaOrigem;
	}

	public int getLinhaDestino() {
		return this.linhaDestino;
	}

	public int getColunaDestino() {
		return this.colunaDestino;
	}

	public int getLinhaOrigem() {
		return this.linhaOrigem;
	}

	public int getColunaOrigem() {
		return this.colunaOrigem;
	}

	public void informarPosicaoDestino(int linhaDestino, int colunaDestino) {
		this.linhaDestino = linhaDestino;
		this.colunaDestino = colunaDestino;
	}
}
