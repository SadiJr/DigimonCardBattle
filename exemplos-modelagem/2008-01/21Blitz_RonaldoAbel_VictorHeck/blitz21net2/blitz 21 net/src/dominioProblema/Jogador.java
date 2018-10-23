package dominioProblema;

public abstract class Jogador {
 
	protected int simbolo;
	 
	protected boolean daVez;
	 
	protected boolean vencedor;
	 
	protected String nome;
	
	protected int pontos;
	 
	public abstract Lance habilitar(ImagemDeTabuleiro estado);
	public abstract void iniciar(String umNome, int umSimbolo);
	public abstract void reiniciar();
	
	public void desabilitar() {
		daVez = false;
	}
	 
	public int informarSimbolo() {
		return simbolo;
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
	public void setPonto(int p){
		pontos=p;
	}
	public int getPonto(){
		return pontos;
	}
}
 
