package DominioDoProblema;

public class ImagemTabuleiro {
	protected int mapa[][] = new int[8][8];
	protected String mensagem;

	public void defineValor(int linha, int coluna, String corPeca) {
		int valor = 0; // 0 - sem cor
		if (corPeca.equals("branca"))
			valor = 1;
		else if (corPeca.equals("preta"))
			valor = 2;
		else if (corPeca.equals("brancaSelect"))
			valor = 3;
		else if (corPeca.equals("pretaSelect"))
			valor = 4;

		mapa[linha][coluna] = valor;
	}

	public void receberMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String informarMensagem() {
		return mensagem;
	}

	public int informarValor(int linha, int coluna) {
		return mapa[linha][coluna];
	}
}
