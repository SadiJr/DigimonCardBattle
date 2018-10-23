package settlers.jogadas;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaUsarCarta implements Jogada {

	private static final long serialVersionUID = 1L;
	
	private int carta;
	
	public JogadaUsarCarta(int carta) {
		this.carta = carta;
	}
	
	public int getCarta() {
		return carta;
	}

}
