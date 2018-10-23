package logica;

public class Nivel {
	protected int numero;
	protected Rodada rodada;
	protected GeneroMusical generoMusical;
	protected GeradorDeQuestoes geradorDeQuestoes;

	public Nivel(int numeroNivel, GeneroMusical generoMusical, GeradorDeQuestoes geradorDeQuestoes) {
		this.numero = numeroNivel;
		this.generoMusical = generoMusical;
		this.geradorDeQuestoes = geradorDeQuestoes;
		rodada = new Rodada(0);
		Questao questao = geradorDeQuestoes.gerarQuestao(generoMusical, null);
		rodada.setQuestao(questao);
	}

	public Rodada getRodadaAtual() {
		return rodada;
	}

	public int getTempoMax() {
		int tempoMax;
		switch (numero) {
			case 0: tempoMax = 30;  break;
			case 1: tempoMax = 25;  break;
			case 2: tempoMax = 20;  break;
			case 3: tempoMax = 15;  break;
			default: tempoMax = 10; break;
		}			
		return tempoMax;
	}

	public int getNumero() {
		return numero;
	}

	public GeneroMusical getGeneroMusical() {
		return generoMusical;
	}
	
	public boolean isUltimaRodada() {
		Rodada rodada = getRodadaAtual();
		int numeroRodada = rodada.getNumero();
		return numeroRodada == 3;
	}

	public void proximaRodada() {	
		int numeroRodada = rodada.getNumero();
		numeroRodada++;
		rodada = new Rodada(numeroRodada);
		Questao questao = geradorDeQuestoes.gerarQuestao(generoMusical, rodada);
		rodada.setQuestao(questao);
	}
}