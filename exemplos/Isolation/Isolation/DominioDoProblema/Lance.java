package DominioDoProblema;

import br.ufsc.inf.leobr.cliente.Jogada;


public class Lance implements Jogada {

	protected int linha;
	protected int coluna;

	/**
	 * 
	 * @param linha
	 * @param coluna
	 */
	public void assumir(int linha, int coluna) {
		this.linha=linha;
		this.coluna=coluna;
	}

	public int informarLinha() {
		return this.linha;
	}

	public int informarColuna() {
		return this.coluna;
	}

}