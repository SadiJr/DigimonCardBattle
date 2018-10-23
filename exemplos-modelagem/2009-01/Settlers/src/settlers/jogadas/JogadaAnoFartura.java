package settlers.jogadas;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaAnoFartura implements Jogada {

	private static final long serialVersionUID = 1L;
	
	private int recurso;
	
	public JogadaAnoFartura(int recurso) {
		this.recurso = recurso;
	}

	public int getRecurso() {
		return recurso;
	}

}
