package dominioProblema;

public class Jogador {
	
	protected Peca peca;
	protected boolean atual;
	protected boolean vencedor;
	protected String nome;
	protected int numVezesQueJogou = 0;
	 
	 
	public Peca getPeca() {
		return peca;
	}
	 
	public boolean getAtual() {
		return atual;
	}
	
	public void setVencedor() {
		vencedor = true;
	}
	 
	public boolean getVencedor() {
		return vencedor;
	}
	 
	public String getNome() {
		return nome;
	}
	 
	public void habilitar() {
		atual = true;
	}
	 
	public void desabilitar() {
		atual = false;
	}
	 
	public void iniciar(String pNome, Peca pPeca) {
		peca = pPeca;
		atual = false;
		vencedor = false;
		nome = pNome;
	}

	public int getNumVezesQueJogou() {
		return numVezesQueJogou;
	}

	public void incrementaNumVezesQueJogou() {
		this.numVezesQueJogou++;
	}
}
