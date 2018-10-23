package logica;

public class AtualizaGUI {
 
	protected int dinheiroApostador;
	 
	protected int valorApostado;
	 
	protected int ganho;
	 
	protected int numeroSorteado;
	 
	public void iniciar(int dinheiroApostador, int valorApostado, int ganho, int numeroSorteado) {
		this.dinheiroApostador = dinheiroApostador;
		this.valorApostado = valorApostado;
		this.ganho = ganho;
		this.numeroSorteado = numeroSorteado;
	}
	 
	public int getValorApostado() {
		return valorApostado;
	}
	 
	public int getDinheiroApostador() {
		return dinheiroApostador;
	}
	 
	public int getGanho() {
		return ganho;
	}
	
	public int getNumeroSorteado() {
		return numeroSorteado;
	}
}
 
