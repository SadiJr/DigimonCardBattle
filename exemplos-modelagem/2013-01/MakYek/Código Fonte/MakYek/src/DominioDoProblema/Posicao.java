package DominioDoProblema;

public class Posicao {

	protected Peca peca;
	protected int linha;
	protected int coluna;

	public Peca getPeca() {
		return this.peca;
	}

	public boolean temPeca() {
		if (peca != null)
			return true;
		else
			return false;
	}

	public String informarCorPeca() {
		return peca.getCor();
	}

	public int getLinha() {
		return this.linha;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public int getColuna() {
		return this.coluna;
	}

	public void retirePeca() {
		peca = null;
	}

	public void criePeca(boolean cor) {
		peca = new Peca();
		if (cor)
			peca.setCor("branca");

		else
			peca.setCor("preta");

	}
}
