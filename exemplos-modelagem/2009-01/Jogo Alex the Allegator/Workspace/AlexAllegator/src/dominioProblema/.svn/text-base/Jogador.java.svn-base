package dominioProblema;

public class Jogador {
 
	protected int simbolo;
	 
	protected boolean daVez;
	 
	protected boolean vencedor;

	protected String nome;
	
	protected int pontuacao;
	 
	
	public void iniciar(String umNome, int umSimbolo) {
		simbolo = umSimbolo;
		daVez = false;
		vencedor = false;
		nome = umNome;
	}
	
	public void reiniciar() {
		daVez = false;
		vencedor = false;
	}
	
	public int getPontuacao() {
		return pontuacao;
	}
	
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public void habilitar(){
		daVez = true;
	}
	public void desabilitar() {
		daVez = false;
	}
	 
	public int informarSimbolo() {
		return simbolo;
	}
	
	public void somarPontos() {
		pontuacao++;
	}
	
	public void zerarPontos() {
		this.setPontuacao(0);
	}
	 
	public String informarNome() {
		return nome;
	}
	
	public boolean informarVencedor() {
		return vencedor;
	}
	 
	public void tornarSeVencedor() {
		vencedor = true;
	}
	 
	public boolean informarDaVez() {
		return daVez;
	}
	
	public void setDaVez(boolean daVez) {
		this.daVez = daVez;
	}	 
}