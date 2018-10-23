package DominioDoProblema;

public class Jogador {
	protected String nome;
	protected boolean jogadorDaVez;
	protected boolean vencedor;
	protected boolean cor;

	public boolean informaDaVez() {
		return jogadorDaVez;
	}

	public void desabilitar() {
		jogadorDaVez = false;
	}

	public void habilitar() {
		jogadorDaVez = true;
	}

	public String informarNome() {
		return nome;
	}

	public void vencedor() {
		vencedor = true;
	}

	public boolean informaVencedor() {
		return vencedor;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String informaCorJogador() {
		if (cor)
			return "branca";
		else
			return "preta";
	}

	public void iniciar() {
		jogadorDaVez = false;
		vencedor = false;

	}

	public void assumeCor(boolean cor) {
		this.cor = cor;
	}
}
