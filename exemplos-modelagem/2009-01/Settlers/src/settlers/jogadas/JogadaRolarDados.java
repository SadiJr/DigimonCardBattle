package settlers.jogadas;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaRolarDados implements Jogada {
	
	private static final long serialVersionUID = 1L;
	
	private int valor1;
	private int valor2;
	
	public JogadaRolarDados(int valor1, int valor2) {
		this.valor1 = valor1;
		this.valor2 = valor2;
	}
	
	public int getValor1() {
		return valor1;
	}
	
	public int getValor2() {
		return valor2;
	}

}
