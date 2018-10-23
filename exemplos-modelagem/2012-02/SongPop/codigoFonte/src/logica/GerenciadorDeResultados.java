package logica;

import rede.Resultado;

public class GerenciadorDeResultados {
	protected Resultado[][][] resultadosJogadores;
	protected double[] pontuacaoTotalJogadores;

	public GerenciadorDeResultados() {
		resultadosJogadores = new Resultado[2][5][4];
		pontuacaoTotalJogadores = new double[2];
	}
	
	public void adicionarResultado(Resultado resultado) {
		int indiceJogador = resultado.getIdJogador() - 1;
		int numeroNivel = resultado.getNumeroNivel();
		int numeroRodada = resultado.getNumeroRodada();	
		double pontuacaoResultado = resultado.getPontuacao();
		resultadosJogadores[indiceJogador][numeroNivel][numeroRodada] = resultado;		
		pontuacaoTotalJogadores[indiceJogador] += pontuacaoResultado; 
	}

	public Resultado[][] getResultadosJogador(int idJogador) {
		return resultadosJogadores[idJogador-1];
	}

	public double getPontuacaoTotalJogador(int idJogador) {
		return pontuacaoTotalJogadores[idJogador-1];
	}

	public void zerarResultados() {
		resultadosJogadores = new Resultado[2][5][4];
	}

	public void zerarPontuacoes() {
		pontuacaoTotalJogadores = new double[2];
	}
}