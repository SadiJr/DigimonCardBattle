package settlers.modelo;

public abstract class Carta {
	
	private Jogador jogador;
	private int turnoRecebimento;

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public int getTurnoRecebimento() {
		return turnoRecebimento;
	}

	public void setTurnoRecebimento(int turnoRecebimento) {
		this.turnoRecebimento = turnoRecebimento;
	}

}
