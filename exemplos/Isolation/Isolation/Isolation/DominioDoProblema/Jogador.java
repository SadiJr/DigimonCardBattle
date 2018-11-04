package DominioDoProblema;

public class Jogador {

	protected String nome;
	protected int fase;
	protected boolean vencedor;
	protected int simbolo;
	protected Posicao ocupando;

	public void iniciar() {
		fase=0;
		vencedor=false;
	}

	/**
	 * 
	 * @param idJogador
	 */
	public void assumirNome(String idJogador) {
		this.nome=idJogador;
	}

	/**
	 * 
	 * @param cor
	 */
	public void assumirCor(int cor) {
		this.simbolo=cor;
	}

	public boolean informarDaVez() {
		return fase!=0;
	}

	public int verificarFase() {
		return fase;
	}

	/**
	 * 
	 * @param linha
	 * @param coluna
	 */
	public boolean verificarAdjacente(int linha, int coluna) {
		return this.ocupando.verificarAdjacente(linha, coluna);
	}

	/**
	 * 
	 * @param nova
	 */
	public void atualizar(Posicao nova) {
		if(this.ocupando==null) {
			this.ocupando=nova;
		}else {
			this.ocupando.esvaziar();
			this.ocupando=nova;
			this.ocupando.modOcupacao(simbolo);
		}
	}

	public void mudarFase() {
		if(this.fase==2) {
			this.fase=0;
		}else {
			this.fase++;
		}
	}

	public Posicao informarPosicao() {
		return this.ocupando;
	}

	public void assumirVencedor() {
		this.vencedor=true;
	}

	public String informarNome() {
		return this.nome;
	}

	public boolean informarVencedor() {
		return this.vencedor;
	}
	
	public void desabilitar() {
		this.fase=0;
	}

	public int informarSimbolo() {
		return this.simbolo;
	}

}