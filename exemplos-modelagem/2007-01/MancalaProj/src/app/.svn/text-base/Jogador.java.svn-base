package app;

public abstract class Jogador {
	 
	protected boolean daVez;
	protected boolean vencedor;
	protected int casaJogada;
	protected String letraJogador;
	
	public Jogador(){
		this.daVez = false;
		this.vencedor = false;
	}
	
	public String retorneLetraJogador(){
		return letraJogador;
	}
	
	public int retorneCasaJogada(){
		return casaJogada;
	}
	
	public void setDaVez(boolean daVez){
		this.daVez = daVez;
	}
	
	public boolean getDaVez(){
		return this.daVez;
	}
	
	public void definirCasaJogada(int casa){
		this.casaJogada = casa;
	}
	
	abstract int realizarJogada();
	
	abstract int realizarJogada(int[] estadoAtual);
	
	public void setVencedor() {
		this.vencedor = true;
	}
	 
}
 
