package DominioDoProblema;

public class Peca {
	protected boolean selecionada;
	protected String cor;

	public String getCor() {
		return this.cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public void setSelecionda(boolean selecionada) {
		this.selecionada = selecionada;
	}

	public boolean getSelecionda() {
		return this.selecionada;
	}
}
