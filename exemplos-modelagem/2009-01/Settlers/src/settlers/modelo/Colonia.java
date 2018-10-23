package settlers.modelo;

public abstract class Colonia {
	
	private PosicaoColonia posicaoColonia;
	private Jogador jogador;
	private int recursosProduzidos;

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public int getRecursosProduzidos() {
		return recursosProduzidos;
	}

	public void setRecursosProduzidos(int recursosProduzidos) {
		this.recursosProduzidos = recursosProduzidos;
	}

	public PosicaoColonia getPosicaoColonia() {
		return posicaoColonia;
	}

	public void setPosicaoColonia(PosicaoColonia posicaoColonia) {
		this.posicaoColonia = posicaoColonia;
	}
	
}
