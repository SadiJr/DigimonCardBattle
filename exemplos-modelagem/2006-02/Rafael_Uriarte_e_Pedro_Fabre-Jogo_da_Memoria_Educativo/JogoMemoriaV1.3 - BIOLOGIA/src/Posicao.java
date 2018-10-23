public class Posicao {
 
	private Carta carta;
	 
	private boolean virada;
	
	public Posicao(int numero){
		virada = false;
		carta = new Carta(numero);
	}
	
	public boolean getVirada() {
		return virada;
	}
	 
	public void setVirada(Boolean virada) {
		this.virada = virada;
	}
	 
	public Carta getPar() {
		return carta.getPar();
	}
	public String[] getInformacoesCartaPOSICAO(){
		return carta.getCaminhoArquivos();
	}
	
	public Carta getCarta() {
		return carta;
	}
	public void setPar(Carta par){
		carta.setPar(par);
	}
}
 
