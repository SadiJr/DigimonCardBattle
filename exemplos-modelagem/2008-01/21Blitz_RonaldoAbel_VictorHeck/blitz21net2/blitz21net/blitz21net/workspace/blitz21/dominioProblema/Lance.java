package dominioProblema;

public class Lance {
 
	protected int linha = 0;
	 
	protected int coluna = 0;
	 
	public int informarLinha() {
		return linha;
	}
	 
	public int informarColuna() {
		return coluna;
	}
	 
	public void assumir(int valorLinha, int valorColuna) {
		linha = valorLinha;
		coluna = valorColuna;
	}
	 
}
 
