package logica;

public class Rodada {
	protected int numero;
	protected Questao questao;
	protected boolean emAndamento;

	public Rodada(int numeroRodada) {
		numero = numeroRodada;
		emAndamento = false;
	}
	
	public boolean isEmAndamento() {
		return emAndamento;
	}
	
	public void setEmAndamento(boolean emAndamento) {
		this.emAndamento = emAndamento;
	}

	public Questao getQuestao() {
		return questao;
	}
	
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public boolean isRodadaMusical() {
		return numero < 2;
	}

	public int getNumero() {
		return numero;
	}

	public int getPontuacaoPorSegundo() {
		int pontuacaoPorSegundo;
		switch (numero) {
			case 0: pontuacaoPorSegundo = 1;
			case 1: pontuacaoPorSegundo = 2;
			case 2: pontuacaoPorSegundo = 5;
			default: pontuacaoPorSegundo = 6;
		}
		return pontuacaoPorSegundo;
	}

	public int getTipoRodada() {
		int tipoRodada;
		if (numero == 0 || numero == 2)
			tipoRodada = 0;
		else
			tipoRodada = 1;
		return tipoRodada;
	}
}