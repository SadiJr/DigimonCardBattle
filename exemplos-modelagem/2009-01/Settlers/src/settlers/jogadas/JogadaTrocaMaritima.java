package settlers.jogadas;

import br.ufsc.inf.leobr.cliente.Jogada;

public class JogadaTrocaMaritima implements Jogada {

	private static final long serialVersionUID = 1L;
	
	private int recursoOfertado;
	private int quantidadeOfertada;
	private int recursoRecebido;
	private int quantidadeRecebida;
	
	public JogadaTrocaMaritima(int recursoOfertado, int quantidadeOfertada, int recursoRecebido, int quantidadeRecebida) {
		this.recursoOfertado = recursoOfertado;
		this.quantidadeOfertada = quantidadeOfertada;
		this.recursoRecebido = recursoRecebido;
		this.quantidadeRecebida = quantidadeRecebida;
	}

	public int getRecursoOfertado() {
		return recursoOfertado;
	}

	public int getQuantidadeOfertada() {
		return quantidadeOfertada;
	}

	public int getRecursoRecebido() {
		return recursoRecebido;
	}

	public int getQuantidadeRecebida() {
		return quantidadeRecebida;
	}

}
