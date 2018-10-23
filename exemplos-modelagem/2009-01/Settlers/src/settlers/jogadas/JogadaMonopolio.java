package settlers.jogadas;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaMonopolio implements Jogada {

	private static final long serialVersionUID = 1L;
	
	private int recurso;
	
	public JogadaMonopolio(int recurso) {
		this.recurso = recurso;
	}

	public int getRecurso() {
		return recurso;
	}

}
