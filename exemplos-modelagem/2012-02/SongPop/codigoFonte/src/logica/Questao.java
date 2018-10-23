package logica;

public class Questao {
	protected String pergunta;
	protected String[] alternativas;
	protected int alternativaCorreta;

	public Questao(String pergunta, String[] alternativas, int alternativaCorreta) {
		this.pergunta = pergunta;
		this.alternativas = alternativas;
		this.alternativaCorreta = alternativaCorreta;
	}

	public String getPergunta() {
		return pergunta;
	}

	public String[] getAlternativas() {
		return alternativas;
	}

	public int getAlternativaCorreta() {
		return alternativaCorreta;
	}
}