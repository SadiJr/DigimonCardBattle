package logica;

public class Jogador {
 
	protected int dinheiro = 0;
	 
	public void zerar() {
		dinheiro = 5000;
	}
	 
	public boolean verificarDisponivel(int valor) {
		return dinheiro >= valor;
	}
	 
	public int atualizaDinheiro(int valor) {
		dinheiro += valor;
		return dinheiro;
	}
	 
}
 
