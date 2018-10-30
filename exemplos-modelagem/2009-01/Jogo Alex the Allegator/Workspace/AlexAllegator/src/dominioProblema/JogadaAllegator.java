package dominioProblema;

public class JogadaAllegator implements br.ufsc.inf.leobr.cliente.Jogada{

	private static final long serialVersionUID = 1L;

	public int linha;
	
	public int coluna;

	public JogadaAllegator(int linha, int coluna) {
		super();
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}	
}