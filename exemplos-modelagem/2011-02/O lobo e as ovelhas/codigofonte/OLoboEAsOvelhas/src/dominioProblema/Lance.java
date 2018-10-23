package dominioProblema;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Lance implements Jogada{
	
	private static final long serialVersionUID = 1L;
	
	protected int linhaOrigem;	
	protected int colunaOrigem;	
	protected int linhaDestino;	
	protected int colunaDestino;
	
	public Lance(){
		linhaOrigem = -1;
		colunaOrigem = -1;
		linhaDestino = -1;
		colunaDestino = -1;
	}
	
	public Lance(int linhaOrigem,int colunaOrigem
			,int linhaDestino, int colunaDestino){
		this.linhaOrigem = linhaOrigem;
		this.colunaOrigem = colunaOrigem;
		this.linhaDestino = linhaDestino;
		this.colunaDestino = colunaDestino;
	}
	
	public void assumirOrigemDestino(int linhaOrigem, int colunaOrigem,
			int linhaDestino, int colunaDestino){
		this.linhaOrigem = linhaOrigem;
		this.colunaOrigem = colunaOrigem;
		this.linhaDestino = linhaDestino;
		this.colunaDestino = colunaDestino;
	}
	
	public int informarLinhaOrigem(){ return linhaOrigem; }
	
	public int informarColunaOrigem(){ return colunaOrigem; }
	
	public int informarLinhaDestino(){ return linhaDestino;	}
	
	public int informarColunaDestino(){ return colunaDestino; }
	
}
