package logica;

public class QuestaoMusical extends Questao {
	protected int idMusica;

	public QuestaoMusical(String pergunta, String[] alternativas, int alternativaCorreta, int idMusica) {
		super(pergunta, alternativas, alternativaCorreta);
		this.idMusica = idMusica;
	}

	public int getIdMusica() {
		return idMusica;
	}
}