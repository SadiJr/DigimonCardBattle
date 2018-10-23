package settlers.jogadas;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaConstruirEstrada implements Jogada {

	private static final long serialVersionUID = 1L;
	
	private int identificador;
	
	public JogadaConstruirEstrada(int identificador) {
		this.identificador = identificador;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

}
