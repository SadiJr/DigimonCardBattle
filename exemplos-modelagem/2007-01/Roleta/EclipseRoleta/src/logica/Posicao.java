package logica;

public class Posicao {
	
	protected int aposta = 0;
	 
	protected int multiplicador;
	 
	protected int[] numerosQueVencem;
	
	public void iniciar(int[] numerosQueVencem, int multiplicador) {
		this.numerosQueVencem = numerosQueVencem;
		this.multiplicador = multiplicador;
	}
	
	public void zerar() {
		aposta = 0;
	}
	 
	public int atualizaAposta(int valor) {
		aposta += valor;
		return aposta;
	}
	 
	public int obterGanho(int numero) {
		for(int i = 0; i < numerosQueVencem.length; i++) {
			if(numerosQueVencem[i] == numero) {
				return multiplicador*aposta;
			}
		}
		return 0;
	}
	 
}
 
