package rede;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Resultado implements Jogada {
	private static final long serialVersionUID = 1L;
	protected double pontuacao;
	protected double tempoDeResposta;
	protected int idJogador;
	protected int numeroNivel;
	protected int numeroRodada;

	public Resultado(int idJogador, int numeroNivel, int numeroRodada, double pontuacaoDaRodada, double tempoDeResposta) {
		this.idJogador = idJogador;
		this.numeroNivel = numeroNivel;
		this.numeroRodada = numeroRodada;
		this.pontuacao = pontuacaoDaRodada;
		this.tempoDeResposta = tempoDeResposta;
	}

	public int getNumeroRodada() {
		return numeroRodada;
	}
	
	public int getNumeroNivel() {
		return numeroNivel;
	}
	
	public int getIdJogador() {
		return idJogador;
	}
	
	public double getPontuacao() {
		return pontuacao;
	}

	public double getTempoDeResposta() {
		return tempoDeResposta;
	}

	public boolean isCorreta() {
		return pontuacao != 0;
	}
}