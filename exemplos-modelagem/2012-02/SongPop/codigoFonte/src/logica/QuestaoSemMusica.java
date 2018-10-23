package logica;

public class QuestaoSemMusica extends Questao {
	protected String complementoPergunta;

	public QuestaoSemMusica(String pergunta, String[] alternativas, int alternativaCorreta, String complemento) {
		super(pergunta, alternativas, alternativaCorreta);
		complementoPergunta = complemento;
	}

	public String getComplementoPergunta() {
		return complementoPergunta;
	}
}